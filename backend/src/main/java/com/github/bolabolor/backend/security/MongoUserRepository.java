package com.github.bolabolor.backend.security;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
    MongoUser findMongoUserByUsername(String username);
}
