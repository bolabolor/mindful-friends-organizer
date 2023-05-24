package com.github.bolabolor.backend.controller;

import com.github.bolabolor.backend.model.Friend;
import com.github.bolabolor.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    Friend addFriend(@RequestBody Friend friend){
        return friendService.addFriend(friend);
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
