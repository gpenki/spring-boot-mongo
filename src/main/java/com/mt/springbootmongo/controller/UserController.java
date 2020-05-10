package com.mt.springbootmongo.controller;

import com.mt.springbootmongo.domain.User;
import com.mt.springbootmongo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public String addNewUsers(User user) {
        LOG.info("addNewUsers -- Saving user.");
          userService.save(user);

        return "redirect:/";
    }

}
