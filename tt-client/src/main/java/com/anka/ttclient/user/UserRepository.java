package com.anka.ttclient.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,Long> {
    Mono<User> findByEmail(String email);
}
