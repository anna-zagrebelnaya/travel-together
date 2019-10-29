package com.anka.traveltogether.friend;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/friends")
@AllArgsConstructor
public class FriendController {

    private final FriendRepository friends;

    @GetMapping
    Flux<Long> getUserFriends(@PathVariable Long userId) {
        return friends.findByUserId(userId)
                .map(Friend::getFriends)
                .flatMapMany(Flux::fromIterable);
    }

    @PostMapping(value = "/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    Mono<Void> addUserFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        return friends.findByUserId(userId)
                .filter(friend -> !friend.getFriends().contains(friendId))
                .doOnNext(f -> log.info("adding friend {}", friendId))
                .map(friend -> friend.addFriend(friendId))
                .flatMap(friends::save)
                .then();
    }

    @DeleteMapping(value = "/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    Mono<Void> deleteUserFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        return friends.findByUserId(userId)
                .filter(friend -> friend.getFriends().contains(friendId))
                .doOnNext(f -> log.info("deleting friend {}", friendId))
                .map(friend -> friend.deleteFriend(friendId))
                .flatMap(friends::save)
                .then();
    }
}
