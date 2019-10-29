package com.anka.traveltogether.friend;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
public class Friend {
    @Id
    private Long userId;
    private Set<Long> friends;

    public Friend(Long userId) {
        this.userId = userId;
    }

    public Friend addFriend(Long friendId) {
        friends.add(friendId);
        return this;
    }

    public Friend deleteFriend(Long friendId) {
        friends.remove(friendId);
        return this;
    }
}
