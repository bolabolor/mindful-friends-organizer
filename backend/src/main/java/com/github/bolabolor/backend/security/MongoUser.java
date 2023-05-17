package com.github.bolabolor.backend.security;

import com.github.bolabolor.backend.friend.Friend;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("mongoUsers")
public record MongoUser(
        @Id
        String id,
        @NotBlank
        @Size(min=2, max=30)
        String username,
        @NotBlank
        @Size(min=4, max=60)
        String password,
        List<Friend> friends
) {
        public MongoUser(String username, String password, List<Friend> friends) {
                this(null, username, password, friends);
        }
}