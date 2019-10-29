package com.anka.ttclient.features.friend;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FriendService {
    Flux<Long> findByUserId(Long id);
    Mono<Void> addUserFriend(Long id, Long friendId);
    Mono<Void> removeUserFriend(Long id, Long friendId);
}
