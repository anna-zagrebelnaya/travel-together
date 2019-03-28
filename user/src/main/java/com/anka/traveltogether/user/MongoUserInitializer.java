package com.anka.traveltogether.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Component
@RequiredArgsConstructor
public class MongoUserInitializer implements SmartInitializingSingleton {

    private final UserRepository users;

    @Override
    public void afterSingletonsInstantiated() {
        String password = "{bcrypt}$2a$10$x.z3t/qSakhH41wqGLW0dOxSIJkp1WSE5VG6jDyEEJttNDndhrjnu";
        User john = new User(1L, "John", "Doe", "johndoe@test.com", password);
        User mary = new User(2L, "Mary", "Roe", "maryroe@test.com", password);
        john.setFriends(singletonList(mary));
        mary.setFriends(singletonList(john));

        User george = new User(3L, "George", "Bush", "bush@test.com", password);
        User dick = new User(4L, "Dick", "Chaney", "dick@test.com", password);
        User liz = new User(5L, "Liz", "Chaney", "annzagrebelnaya@gmail.com", password);
        george.setFriends(asList(dick, liz));
        dick.setFriends(asList(george, liz));
        liz.setFriends(asList(dick, george));

        this.users.saveAll(asList(john, mary, george, dick, liz))
                .subscribe(user -> System.out.println("user " + user.getFirstName() + " initialized"));
    }
}
