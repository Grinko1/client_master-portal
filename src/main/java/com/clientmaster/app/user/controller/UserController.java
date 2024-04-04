package com.clientmaster.app.user.controller;

import com.clientmaster.app.user.entity.MyUser;
import com.clientmaster.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    @PostMapping("/registration")
    public MyUser registration(@RequestBody MyUser user){
        return userService.saveUser(user);
    }
    @GetMapping("/users")
    public List<MyUser> getAll(){
        return userService.getAllUsers();
    }

}
