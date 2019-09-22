package com.anka.traveltogether.friend;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FriendRepository extends ReactiveCrudRepository<Friend,Long> {
    Mono<Friend> findByUserId(Long userId);
}
