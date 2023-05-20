package com.github.bolabolor.backend.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final CloudinaryService cloudinaryService;
    public List<Friend> getAllFriends(){
        return friendRepository.findAll();
    }
    public Friend addFriend(Friend friend, MultipartFile image) throws IOException{
        String id = UUID.randomUUID().toString();
        Friend friendToSave = friend.withId(id);

        if (image != null) {
            String url = cloudinaryService.uploadImage(image);
            friendToSave = friendToSave.withUrl(url);
        }
        return friendRepository.save(friendToSave);
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
