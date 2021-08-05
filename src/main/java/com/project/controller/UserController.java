package com.project.controller;


import com.project.model.User;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/basic")
    public List<User> getAllUsers()
    {
        List<User> list =userService.getAllUsers();
        System.out.println(list);
        return list;
    }
}

