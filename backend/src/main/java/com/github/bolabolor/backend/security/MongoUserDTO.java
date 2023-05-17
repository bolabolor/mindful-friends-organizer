package com.github.bolabolor.backend.security;

import com.github.bolabolor.backend.friend.Friend;

import java.util.List;

public record MongoUserDTO(
        String id,
        String username,
        String password,
        List<Friend> friends) {
    public MongoUserDTO(String username, String password, List<Friend> friends){
        this(null, username, password, friends);
    }
}
