package com.anka.traveltogether.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
public class MongoFriendInitializer implements SmartInitializingSingleton {

    private final FriendRepository users;

    @Override
    public void afterSingletonsInstantiated() {
        Friend john = new Friend(1L);
        Friend mary = new Friend(2L);
        john.setFriends(singletonList(2L));
        mary.setFriends(singletonList(1L));

        Friend george = new Friend(3L);
        Friend dick = new Friend(4L);
        Friend liz = new Friend(5L);
        george.setFriends(asList(4L, 5L));
        dick.setFriends(asList(3L, 5L));
        liz.setFriends(asList(4L, 3L));

        this.users.saveAll(asList(john, mary, george, dick, liz))
                .subscribe(friend -> System.out.println("friends for user " + friend.getUserId() + " initialized"));
    }
}
