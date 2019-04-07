package com.anka.ttclient.security;

import com.anka.ttclient.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ModelAttribute("currentUser")
    Mono<User> currentUser(@AuthenticationPrincipal Mono<User> currentUser) {
        System.out.println("curUser " + currentUser); //TODO: fix - mono is empty
        return currentUser;
    }
}
