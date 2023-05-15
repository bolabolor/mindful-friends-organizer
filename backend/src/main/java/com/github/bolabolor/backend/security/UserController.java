package com.github.bolabolor.backend.security;

import com.github.bolabolor.backend.model.MongoUserDTO;
import com.github.bolabolor.backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String getMe(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }

    @PostMapping("/signup")
    public ResponseEntity<MongoUserDTO> signup(@RequestBody MongoUserDTO mongoUserDTO) {
        userService.signupMongoUser(mongoUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
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