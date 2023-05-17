package com.github.bolabolor.backend.friend;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface FriendRepository extends MongoRepository<Friend, String> {

}
