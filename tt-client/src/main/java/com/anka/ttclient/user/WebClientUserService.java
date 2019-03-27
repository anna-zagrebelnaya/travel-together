package com.anka.ttclient.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientUserService implements UserService {

    private final WebClient webClient;
    private final String usersUrl;

    public WebClientUserService(WebClient webClient, @Value("${users-url}") String usersUrl) {
        this.webClient = webClient;
        this.usersUrl = usersUrl;
    }

    @Override
    public Mono<User> findById(Long id) {
        return this.webClient.get()
                .uri(this.usersUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return this.webClient.get()
                .uri(this.usersUrl + "?email={email}", email)
                .retrieve()
                .bodyToMono(User.class);
    }
}
