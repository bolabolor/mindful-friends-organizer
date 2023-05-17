package com.github.bolabolor.backend.friend;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {
    private final FriendService friendService;
    FriendController(FriendService friendService){
        this.friendService = friendService;
    }
    @GetMapping
    List<Friend> getAll(){
        return friendService.getAll();
    }
    @PostMapping
    Friend postFriend(@RequestPart("data") @Valid Friend friend, @RequestPart(name = "file", required = false) MultipartFile image) throws IOException{
        return friendService.save(friend, image);
    }
    @GetMapping("{id}")
    Friend getFriendById(@PathVariable String id){
        return friendService.getById(id);
    }
    @PutMapping(path = {"{id}/update", "{id}"})
    Friend updateFriend(@PathVariable String id, @RequestBody Friend friend){
    if (!friend.id().equals(id)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id in the url does not match the request body's id");
    }
        return friendService.update(friend);
    }
    @DeleteMapping("{id}")
    void deleteFriend(@PathVariable String id) {
        friendService.delete(id);
    }
}
