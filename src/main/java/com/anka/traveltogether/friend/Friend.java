package com.anka.traveltogether.friend;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Friend {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
