package com.github.bolabolor.backend.service;
import com.github.bolabolor.backend.model.Friend;
import com.github.bolabolor.backend.repository.FriendRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FriendServiceTest {

    FriendRepository friendRepository = mock(FriendRepository.class);
    FriendService friendService = new FriendService(friendRepository);


    @Test
    void getAllFriendsReturnEmptyList() {
        //GIVE
        List<Friend> expected = Collections.emptyList();
        when(friendRepository.findAll()).thenReturn(Collections.emptyList());
        //WHEN
        List<Friend> actual = friendService.getAllFriends();
        //THEN
        verify(friendRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getFriendById() {

        String id = "1234";
        Friend expected = new Friend("123", "Water", "");
        when(friendRepository.findById(id)).thenReturn(Optional.of(expected));
        // WHEN
        Friend actual = friendService.getFriendById(id);

        verify(friendRepository).findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void addFriend() {
        //GIVEN
        Friend friend = new Friend( "123", "Water", "");
        Mockito.when(friendRepository.save(friend)).thenReturn(friend);
        // WHEN
        Friend actual = friendService.addFriend(friend);

        //THEN
        verify(friendRepository).save(friend);
        assertEquals(friend, actual);
    }

    @Test
    void getFriendById_whenFriendNotExist_thenReturnException() {
        //GIVEN
        String id = "123";
        //WHEN
        when(friendRepository.findById(id)).thenThrow(new NoSuchElementException());
        // THEN
        assertThrows(NoSuchElementException.class, () -> friendService.getFriendById(id));
    }
    @Test
    void deleteFriendById_verify() {
        // GIVEN
        String id = "123";
        // WHEN
        friendService.deleteFriend(id);
        // THEN
        verify(friendRepository).deleteById(id);
    }

    @Test
    void updateFriendById(){
        //GIVEN
        String id = "123";
        Friend friend = new Friend("123", "Water", "");
        //WHEN
        when(friendRepository.findById(id)).thenReturn(Optional.of(friend));
        friendService.updateFriend(friend);
        //THEN
         verify(friendRepository).save(friend);
    }

    @Test
    void getAllFriends() {
        // given
        Friend testFriend = new Friend("", "", "");
        Mockito.when(friendRepository.findAll())
                .thenReturn(Collections.singletonList(testFriend));
        // when
        List<Friend> actual = friendService.getAllFriends();
        // then
        Assertions.assertThat(actual)
                .containsExactly(testFriend);
    }
}