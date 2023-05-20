package com.github.bolabolor.backend.friend;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

public record Friend (
        @Id
    String id,
        @NotBlank
        @Size(min = 1, max = 16)
    String name,
    String url)
{
    public Friend(String name){
        this(null, name, null);
    }
    public Friend withId(String id) {
        return new Friend(id, name, url);
    }
    public Friend withUrl(String url) {
        return new Friend(id, name, url);
    }
}
