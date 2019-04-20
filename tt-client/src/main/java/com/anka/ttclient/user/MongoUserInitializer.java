package com.anka.ttclient.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class MongoUserInitializer implements SmartInitializingSingleton {

    private final UserRepository users;

    @Override
    public void afterSingletonsInstantiated() {
        String password = "{bcrypt}$2a$10$x.z3t/qSakhH41wqGLW0dOxSIJkp1WSE5VG6jDyEEJttNDndhrjnu";
        User john = new User(1L, "John", "Doe", "johndoe@test.com", password);
        User mary = new User(2L, "Mary", "Roe", "maryroe@test.com", password);

        User george = new User(3L, "George", "Bush", "bush@test.com", password);
        User dick = new User(4L, "Dick", "Chaney", "dick@test.com", password);
        User liz = new User(5L, "Liz", "Chaney", "annzagrebelnaya@gmail.com", password);

        this.users.saveAll(asList(john, mary, george, dick, liz))
                .subscribe(user -> System.out.println("users " + user.getFirstName() + " initialized"));
    }
}
