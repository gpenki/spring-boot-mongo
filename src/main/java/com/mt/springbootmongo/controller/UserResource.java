package com.mt.springbootmongo.controller;

import com.mt.springbootmongo.domain.User;
import com.mt.springbootmongo.exception.ResourceNotFoundException;
import com.mt.springbootmongo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    private final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping( value="", produces = "application/json; charset=utf-8")
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}", produces = "application/json; charset=utf-8")
    public User getUser(@PathVariable String userId) throws ResourceNotFoundException {
        LOG.info("Getting user with ID: {}.", userId);
        User user= userService.getUserById(userId);
        if(StringUtils.isEmpty(user)){
            user= userService.getUserByName(userId);
        }
        return user;
    }

    @PostMapping(value = "/save", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    public User addNewUser(@RequestBody User user) {
        LOG.info("Saving user.");
        return userService.save(user);
    }

    @PutMapping(value = "/save/{userId}", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
    public User updateUsers(@RequestBody User user, @PathVariable String userId ) {
        LOG.info("updating user.");
        return userService.updateUserById(user,userId);
    }

    @DeleteMapping(value = "/delete/{userId}", produces = "application/text; charset=utf-8")
    public String deleteUser(@PathVariable String userId ) {
        LOG.info("deleting user.");
        return userService.deleteUserById(userId);
    }
}
