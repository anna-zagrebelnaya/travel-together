package com.anka.ttclient.features.friend;

import com.anka.ttclient.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class FriendsController {

    private final WebClientFriendService friends;

    @RequestMapping(value = "/friends", method = RequestMethod.POST)
    public Mono<String> addFriend(@AuthenticationPrincipal Mono<User> currentUser, FriendModel friendModel) {
        return currentUser
                .flatMap(u -> friends.addUserFriend(u.getId(), friendModel.getFriendId()))
                .thenReturn("redirect:/");
    }

    @RequestMapping(value = "/friends/{friendId}/delete", method = RequestMethod.POST)
    public Mono<String> deleteFriendTemp(@AuthenticationPrincipal Mono<User> currentUser, @PathVariable Long friendId) {
        return currentUser
                .flatMap(u -> friends.removeUserFriend(u.getId(), friendId))
                .thenReturn("redirect:/");
    }

    @RequestMapping(value = "/friends/{friendId}", method = RequestMethod.DELETE)
    public Mono<String> deleteFriend(@AuthenticationPrincipal Mono<User> currentUser, @PathVariable Long friendId) {
        return currentUser
                .flatMap(u -> friends.removeUserFriend(u.getId(), friendId))
                .thenReturn("redirect:/");
    }
}
