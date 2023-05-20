package com.github.bolabolor.backend;

import com.github.bolabolor.backend.friend.CloudinaryService;
import com.github.bolabolor.backend.friend.Friend;
import com.github.bolabolor.backend.friend.FriendRepository;
import com.github.bolabolor.backend.friend.FriendService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FriendServiceTest {

    FriendRepository friendRepository = mock(FriendRepository.class);
    CloudinaryService cloudinaryService = mock(CloudinaryService.class);
    FriendService friendService = new FriendService(friendRepository, cloudinaryService);

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
        Friend expected = new Friend("");
        when(friendRepository.findById(id)).thenReturn(Optional.of(expected));
        // WHEN
        Friend actual = friendService.getFriendById(id);

        verify(friendRepository).findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void addFriend() throws IOException {
        //GIVEN
        Friend friendToSave = new Friend("123", "Air", null);

        MultipartFile file = Mockito.mock(MultipartFile.class);

        when(cloudinaryService.uploadImage(any())).thenReturn("url");
        when(friendRepository.save(friendToSave)).thenReturn(friendToSave);
        //WHEN
        Friend actual = friendService.addFriend(friendToSave, file);
        //THEN
        Friend expected = new Friend("123", "Air", null);

        verify(cloudinaryService).uploadImage(any());
        verify(friendRepository).save(friendToSave);
        assertEquals(expected, actual);
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