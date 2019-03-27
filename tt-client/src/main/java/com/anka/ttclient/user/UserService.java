package com.anka.ttclient.user;

import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> findById(Long id);
    Mono<User> findByEmail(String email);
}
