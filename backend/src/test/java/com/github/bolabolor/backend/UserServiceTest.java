package com.github.bolabolor.backend;

import com.github.bolabolor.backend.security.MongoUser;
import com.github.bolabolor.backend.security.MongoUserRepository;
import com.github.bolabolor.backend.security.MongoUsersDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {
    MongoUserRepository mongoUserRepository = mock(MongoUserRepository.class);
    MongoUsersDetailsService mongoUsersDetailsService = new MongoUsersDetailsService(mongoUserRepository);

    @Test
    void getMongoUserByName() {

        String username = "testUser";
        String password = "1234";

        MongoUser expected = new MongoUser(
                "test1",
                username, password
        );
        when(mongoUserRepository.findMongoUserByUsername(username)).thenReturn(Optional.of(expected));
        // WHEN
        UserDetails actual = mongoUsersDetailsService.loadUserByUsername(username);

        verify(mongoUserRepository).findMongoUserByUsername(username);
        assertEquals(expected.username(), actual.getUsername());
    }
}
