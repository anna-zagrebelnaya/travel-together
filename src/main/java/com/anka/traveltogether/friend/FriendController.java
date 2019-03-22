package com.anka.traveltogether.friend;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/friends")
@AllArgsConstructor
public class FriendController {

    private final FriendRepository friends;

    @GetMapping
    Flux<Friend> getAllFriends() {
        return friends.findAll();
    }
}
