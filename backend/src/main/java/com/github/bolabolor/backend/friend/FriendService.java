package com.github.bolabolor.backend.friend;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FriendService {
    private final FriendRepository friendRepository;
    private final CloudinaryService cloudinaryService;

    FriendService(FriendRepository friendRepository){
        this.friendRepository = friendRepository;
        this.cloudinaryService = cloudinaryService;
    }
    List<Friend> getAll(){
        return friendRepository.findAll();
    }
    public Friend save(Friend friend, MultipartFile image) throws IOException{
        String id = UUID.randomUUID().toString();
        Friend friendToSave = friend.withId(id);

        if (image != null) {
            String url = cloudinaryService.uploadImage(image);
            friendToSave = friendToSave.withUrl(url);
        }

        return friendRepository.save(friendToSave);
    }
    public Friend getById(String id) {
        return friendRepository.findById(id).orElseThrow();
    }

    public Friend update(Friend friend) {
        return friendRepository.save(friend);
    }

    public void delete(String id) {
        friendRepository.deleteById(id);
    }
}