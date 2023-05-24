package com.github.bolabolor.backend.model;

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

}
