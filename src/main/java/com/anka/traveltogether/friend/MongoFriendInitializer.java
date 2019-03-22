package com.anka.traveltogether.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoFriendInitializer implements SmartInitializingSingleton {

    private final FriendRepository friends;

    @Override
    public void afterSingletonsInstantiated() {
        this.friends.save(new Friend(1L, "John", "Doe", "johndoe@test.com")).block();
        this.friends.save(new Friend(2L, "Mary", "Roe", "maryroe@test.com")).block();
        this.friends.save(new Friend(3L, "George", "Bush", "bush@test.com")).block();
    }
}
