package com.github.bolabolor.backend.service;
import com.github.bolabolor.backend.model.Friend;
import com.github.bolabolor.backend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final CloudinaryService cloudinaryService;
    private final IdService idService;
    public List<Friend> getAllFriends(){
        return friendRepository.findAll();
    }
    public Friend addFriend(Friend friend, MultipartFile image) throws IOException{
        String id = idService.createRandomId();
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
