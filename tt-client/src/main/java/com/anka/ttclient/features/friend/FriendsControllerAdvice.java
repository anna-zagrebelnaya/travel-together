package com.anka.ttclient.features.friend;

import com.anka.ttclient.user.User;
import com.anka.ttclient.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ControllerAdvice
@RequiredArgsConstructor
public class FriendsControllerAdvice {

    private final WebClientFriendService friends;
    private final UserRepository users;

    @ModelAttribute("friendsData")
    Flux<User> friendsData(@AuthenticationPrincipal Mono<User> currentUser) {
        return currentUser
                .flatMapMany(u -> friends.findByUserId(u.getId()))
                .flatMap(users::findById);
    }

    @ModelAttribute("friend")
    public FriendModel prepareFriendModel() {
        return new FriendModel();
    }
}
