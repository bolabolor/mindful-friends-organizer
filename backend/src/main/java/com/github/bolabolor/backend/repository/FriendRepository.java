package com.github.bolabolor.backend.repository;
import com.github.bolabolor.backend.model.Friend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends MongoRepository<Friend, String> {

}
