package com.github.bolabolor.backend.service;
import com.github.bolabolor.backend.model.Friend;
import com.github.bolabolor.backend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    public List<Friend> getAllFriends(){
        return friendRepository.findAll();
    }
    public Friend addFriend(Friend friend) {
        return friendRepository.save(friend);
    }
    public Friend getFriendById(String id) {
        return friendRepository.findById(id).orElseThrow();
    }

    public Friend updateFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    public void deleteFriend(String id) {
        friendRepository.deleteById(id);
    }
}
