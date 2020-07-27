package com.zyd.learn.security.control;

import com.zyd.learn.security.dao.entities.UserDO;
import com.zyd.learn.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public UserDO getUserByName(@RequestParam String name) {
        return userService.getByName(name);
    }
}
