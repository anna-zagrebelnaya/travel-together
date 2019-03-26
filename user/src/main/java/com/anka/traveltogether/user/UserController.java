package com.anka.traveltogether.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository users;

    @GetMapping
    Mono<User> getUserByEmail(@RequestParam String email) {
        return users.findByEmail(email);
    }

    @GetMapping("/{userId}")
    Mono<User> getUserById(@PathVariable Long userId) {
        return users.findById(userId);
    }

    @GetMapping("/{userId}/friends")
    Flux<User> getUserFriends(@PathVariable Long userId) {
        return users.findById(userId)
                .flatMapMany(user -> Flux.fromIterable(user.getFriends()));
    }
}
