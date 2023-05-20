package com.github.bolabolor.backend.friend;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    @GetMapping
    List<Friend> getAllFriends(){
        return friendService.getAllFriends();
    }
    @PostMapping
    Friend addFriend(@RequestPart("data") @Valid Friend friend, @RequestPart(name = "file", required = false) MultipartFile image) throws IOException{
        return friendService.addFriend(friend, image);
    }
    @GetMapping("{id}")
    Friend getFriendById(@PathVariable String id){
        return friendService.getFriendById(id);
    }
    @PutMapping(path = {"{id}/update", "{id}"})
    Friend updateFriend(@PathVariable String id, @RequestBody Friend friend){
    if (!friend.id().equals(id)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id in the url does not match the request body's id");
    }
        return friendService.updateFriend(friend);
    }
    @DeleteMapping("{id}")
    void deleteFriend(@PathVariable String id) {
        friendService.deleteFriend(id);
    }
}
