package com.anka.traveltogether.friend;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/friends")
@AllArgsConstructor
public class FriendController {

    private final FriendRepository friends;

    @GetMapping
    Flux<Long> getUserFriends(@RequestParam Long userId) {
        return friends.findByUserId(userId)
                .map(Friend::getFriends)
                .flatMapMany(Flux::fromIterable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    Mono<Void> addUserFriend(@RequestParam Long userId, @RequestParam Long newFriendId) {
        return friends.findByUserId(userId)
                .filter(friend -> !friend.getFriends().contains(newFriendId))
                .doOnNext(f -> log.info("adding friend {}", newFriendId))
                .map(friend -> friend.addFriend(newFriendId))
                .flatMap(friends::save)
                .then();
    }
}
