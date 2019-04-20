package com.anka.ttclient.features.friend;

import reactor.core.publisher.Flux;

public interface FriendService {
    Flux<Friend> findByUserId(Long id);
}
