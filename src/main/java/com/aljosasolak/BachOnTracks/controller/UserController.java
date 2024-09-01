package com.aljosasolak.BachOnTracks.controller;


import com.aljosasolak.BachOnTracks.model.User;
import com.aljosasolak.BachOnTracks.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("{id}")
    User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }
}
