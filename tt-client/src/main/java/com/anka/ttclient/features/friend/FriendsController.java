package com.anka.ttclient.features.friend;

import com.anka.ttclient.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class FriendsController {

    private final WebClientFriendService friends;

    @RequestMapping(value = "/friends/create", method = RequestMethod.POST)
    public Mono<String> insertGuest(@AuthenticationPrincipal Mono<User> currentUser, FriendModel friendModel) {
        return currentUser
                .flatMap(u -> friends.addUserFriend(u.getId(), friendModel.getFriendId()))
                .thenReturn("redirect:/");
    }
}
