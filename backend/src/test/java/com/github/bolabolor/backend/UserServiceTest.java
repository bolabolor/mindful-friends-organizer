package com.github.bolabolor.backend;

import com.github.bolabolor.backend.security.MongoUserDTO;
import com.github.bolabolor.backend.security.MongoUser;
import com.github.bolabolor.backend.security.MongoUserRepository;
import com.github.bolabolor.backend.security.MongoUsersDetailsService;
import com.github.bolabolor.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {
    MongoUserRepository mongoUserRepository = mock(MongoUserRepository.class);
    MongoUsersDetailsService mongoUsersDetailsService = new MongoUsersDetailsService(mongoUserRepository);
    PasswordEncoder encoder = mock(PasswordEncoder.class);


   private final UserService userService = new UserService(mongoUserRepository, encoder);
    @Test
    void expectSignupMongoUser_failed() {
        MongoUserDTO mongoUserDTO = new MongoUserDTO(UUID.randomUUID().toString(),
                "username", "password", Collections.emptyList());
        String encodedPassword = encoder.encode(mongoUserDTO.password());
        MongoUser encodedUser = new MongoUser(mongoUserDTO.username(),
                encodedPassword, Collections.emptyList());
        when(encoder.encode("password")).thenReturn("password");
        when(mongoUserRepository.findMongoUserByUsername(mongoUserDTO.username())).thenReturn(Optional.of(encodedUser));

        assertThrows(IllegalArgumentException.class, () -> userService.signupMongoUser(mongoUserDTO));
    }

    @Test
    void expectSignupMongoUser_successfull() {
        //Given
        MongoUserDTO mongoUserDTO = new MongoUserDTO(UUID.randomUUID().toString(),
                "username", "password", Collections.emptyList());

        String encodedPassword = encoder.encode(mongoUserDTO.password());
        MongoUser encodedUser = new MongoUser(mongoUserDTO.username(), encodedPassword, Collections.emptyList());

        when(mongoUserRepository.findMongoUserByUsername(mongoUserDTO.username())).thenReturn(Optional.empty());
        when(mongoUserRepository.save(encodedUser)).thenReturn(encodedUser);
        MongoUser actual = userService.signupMongoUser(mongoUserDTO);

        assertEquals(actual.username(), mongoUserDTO.username());

    }

    @ExtendWith(MockitoExtension.class)
    @Test
    void getMongoUserByName() {

        String username = "username";
        String password = "password";

        MongoUser expected = new MongoUser(
                "test1",
                username, password, Collections.emptyList()
        );
        when(mongoUserRepository.findMongoUserByUsername(username)).thenReturn(Optional.of(expected));
        // WHEN
        UserDetails actual = mongoUsersDetailsService.loadUserByUsername(username);
        //Then
        verify(mongoUserRepository).findMongoUserByUsername(username);
        assertEquals(expected.username(), actual.getUsername());
    }

}