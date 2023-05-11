package com.github.bolabolor.backend;

import com.github.bolabolor.backend.model.MongoUserDTO;
import com.github.bolabolor.backend.security.MongoUser;
import com.github.bolabolor.backend.security.MongoUserRepository;
import com.github.bolabolor.backend.security.MongoUsersDetailsService;
import com.github.bolabolor.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {
    MongoUserRepository mongoUserRepository = mock(MongoUserRepository.class);
    MongoUsersDetailsService mongoUsersDetailsService = new MongoUsersDetailsService(mongoUserRepository);
    private UserService userService;
    @Mock
    private PasswordEncoder encoder;


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

    @Test
    void expectSuccessfullySignupMongoUser() {
        MongoUserDTO mongoUserDTO = new MongoUserDTO(UUID.randomUUID().toString(),
                "username", "password");
        String encodedPassword = encoder.encode(mongoUserDTO.password());
        MongoUser encodedUser = new MongoUser(mongoUserDTO.username(), encodedPassword);
        when(mongoUserRepository.findMongoUserByUsername(mongoUserDTO.username())).thenReturn(Optional.empty());

        userService.signupMongoUser(mongoUserDTO);

        verify(mongoUserRepository).save(encodedUser);

    }
}
