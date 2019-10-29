package com.anka.ttclient.features.friend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebClientFriendService implements FriendService {

    private final WebClient webClient;
    private final String friendsUrl;

    public WebClientFriendService(WebClient webClient, @Value("${friends-url}") String friendsUrl) {
        this.webClient = webClient;
        this.friendsUrl = friendsUrl;
    }

    @Override
    public Flux<Long> findByUserId(Long userId) {
        return this.webClient.get()
                .uri(this.friendsUrl, userId)
                .retrieve()
                .bodyToFlux(Long.class);
    }

    @Override
    public Mono<Void> addUserFriend(Long userId, Long friendId) {
        return this.webClient.post()
                .uri(this.friendsUrl + "/{friendId}", userId, friendId)
                .retrieve()
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> removeUserFriend(Long userId, Long friendId) {
        return this.webClient.delete()
                .uri(this.friendsUrl + "/{friendId}", userId, friendId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
