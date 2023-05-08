package com.github.bolabolor.backend.model;

public record MongoUserDTO(
        String id,
        String username,
        String password) {
    public MongoUserDTO(String username, String password){
        this(null, username, password);
    }
}
