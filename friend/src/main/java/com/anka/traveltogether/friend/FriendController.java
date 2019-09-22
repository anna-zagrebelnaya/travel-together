package com.anka.traveltogether.friend;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
}
