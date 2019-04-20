package com.anka.ttclient.features.friend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class WebClientFriendService implements FriendService {

    private final WebClient webClient;
    private final String friendsUrl;

    public WebClientFriendService(WebClient webClient, @Value("${friends-url}") String friendsUrl) {
        this.webClient = webClient;
        this.friendsUrl = friendsUrl;
    }

    //TODO: Finalize this on friends service side and test
    @Override
    public Flux<Friend> findByUserId(Long id) {
        return this.webClient.get()
                .uri(this.friendsUrl + "/{id}", id)
                .retrieve()
                .bodyToFlux(Friend.class);
    }
}
