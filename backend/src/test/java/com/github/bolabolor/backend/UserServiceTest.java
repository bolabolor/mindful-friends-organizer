package com.github.bolabolor.backend;

import com.github.bolabolor.backend.model.MongoUserDTO;
import com.github.bolabolor.backend.security.MongoUser;
import com.github.bolabolor.backend.security.MongoUserRepository;
import com.github.bolabolor.backend.security.MongoUsersDetailsService;
import com.github.bolabolor.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {
    MongoUserRepository mongoUserRepository = mock(MongoUserRepository.class);
    MongoUsersDetailsService mongoUsersDetailsService = new MongoUsersDetailsService(mongoUserRepository);
    @Mock
    private PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    private UserService userService = new UserService(mongoUserRepository, encoder);
    @Test
    void expectSignupMongoUser_failed() {
        MongoUserDTO mongoUserDTO = new MongoUserDTO(UUID.randomUUID().toString(),
                "username", "password");
        String encodedPassword = encoder.encode(mongoUserDTO.password());
        MongoUser encodedUser = new MongoUser(mongoUserDTO.username(),
                encodedPassword);
        when(mongoUserRepository.findMongoUserByUsername(mongoUserDTO.username())).thenReturn(Optional.of(encodedUser));

        assertThrows(IllegalArgumentException.class, () -> userService.signupMongoUser(mongoUserDTO));
    }

    @Test
    void expectSignupMongoUser_successfull() {
        MongoUserDTO mongoUserDTO = new MongoUserDTO(UUID.randomUUID().toString(),
                "username", "password");
        String encodedPassword = encoder.encode(mongoUserDTO.password());
        MongoUser encodedUser = new MongoUser(mongoUserDTO.username(), encodedPassword);
        when(mongoUserRepository.findMongoUserByUsername(mongoUserDTO.username())).thenReturn(Optional.empty());

        MongoUser expected = userService.signupMongoUser(mongoUserDTO);

        MongoUser actual = verify(mongoUserRepository).save(encodedUser);
        assertEquals(actual.username(), expected.username());

    }

    @ExtendWith(MockitoExtension.class)
    @Test
    void getMongoUserByName() {

        String username = "username";
        String password = "password";

        MongoUser expected = new MongoUser(
                "test1",
                username, password
        );
        when(mongoUserRepository.findMongoUserByUsername(username)).thenReturn(Optional.of(expected));
        // WHEN
        UserDetails actual = mongoUsersDetailsService.loadUserByUsername(username);
        //Then
        verify(mongoUserRepository).findMongoUserByUsername(username);
        assertEquals(expected.username(), actual.getUsername());
    }

}
