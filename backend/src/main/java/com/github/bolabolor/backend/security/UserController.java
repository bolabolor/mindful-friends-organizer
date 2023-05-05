package com.github.bolabolor.backend.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public String getMe(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
    @Autowired
    private MongoUserRepository mongoUserRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody MongoUser mongoUser){
        if(mongoUserRepository.findMongoUserByUsername(mongoUser.username()) != null){
            return "This user already exists";
        }
        mongoUserRepository.save(mongoUser);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    @PostMapping("/logout")
    public void logout(HttpSession httpSession){
        httpSession.invalidate();
        SecurityContextHolder.clearContext();
    }
}


