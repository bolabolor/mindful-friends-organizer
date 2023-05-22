package com.github.bolabolor.backend.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdService {
    public String createRandomId() {
        return UUID.randomUUID().toString();
    }
}
