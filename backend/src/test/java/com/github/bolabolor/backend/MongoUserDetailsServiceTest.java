package com.github.bolabolor.backend;

import com.github.bolabolor.backend.security.MongoUser;
import com.github.bolabolor.backend.security.MongoUserRepository;
import com.github.bolabolor.backend.security.MongoUsersDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MongoUserDetailsServiceTest {

    private MongoUsersDetailsService detailsService;
    @Mock
    private MongoUserRepository mongoUserRepository;

    @BeforeEach
    void setup() {
        detailsService = new MongoUsersDetailsService(mongoUserRepository);
    }

    @Test
    void expectSuccessfulUsername() {
        //Given
        MongoUser mongoUser = new MongoUser(UUID.randomUUID().toString(),
                "username", "password");
        when(mongoUserRepository.findMongoUserByUsername(mongoUser.username())).thenReturn(Optional.of(mongoUser));
        //When
        detailsService.loadUserByUsername(mongoUser.username());
        //Then
        verify(mongoUserRepository).findMongoUserByUsername(mongoUser.username());

    }

    @Test
    void expectUnsuccessfulUsername() {
        //When
        when(mongoUserRepository.findMongoUserByUsername("username")).thenReturn(Optional.empty());
        //Then
        assertThrows(UsernameNotFoundException.class, () -> detailsService.loadUserByUsername("username"));
    }
}