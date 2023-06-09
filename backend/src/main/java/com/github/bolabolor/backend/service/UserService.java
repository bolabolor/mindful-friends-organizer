package com.github.bolabolor.backend.service;
import com.github.bolabolor.backend.model.MongoUserDTO;
import com.github.bolabolor.backend.model.MongoUser;
import com.github.bolabolor.backend.repository.MongoUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MongoUserRepository mongoUserRepository;
    private final PasswordEncoder encoder;

    public UserService(MongoUserRepository mongoUserRepository, PasswordEncoder encoder) {
        this.mongoUserRepository = mongoUserRepository;
        this.encoder = encoder;
    }

    public MongoUser signupMongoUser(MongoUserDTO mongoUserDTO) {

        if (mongoUserRepository.findMongoUserByUsername(mongoUserDTO.username()).isPresent()) {
            throw new IllegalArgumentException("The username already exists.");
        }
            
        String encodedPassword = encoder.encode(mongoUserDTO.password());
        MongoUser encodedUser = new MongoUser(mongoUserDTO.username(), encodedPassword, mongoUserDTO.friends());
        return mongoUserRepository.save(encodedUser);
  
    }
}