package com.project.controller;

import com.project.model.RespBean;
import com.project.model.User;
import com.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Sweary
 * @packagename:com.project.controller
 * @classname:UserInfoController
 * @date 2021/7/28 - 18:01
 */
@CrossOrigin
@RestController
public class UserInfoController {
    @Autowired
    UserService userService;

    @GetMapping("/user/info")
    public User getCurrentUser(Authentication authentication) {
        return ((User) authentication.getPrincipal());
    }

    @PutMapping("/user/info")
    public RespBean updateUser(@RequestBody User user, Authentication authentication) {
        if (userService.updateUser(user) == 1) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authentication.getAuthorities()));
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PutMapping("/user/pass")
    public RespBean updateUserPassword(@RequestBody Map<String, Object> info) {
        String oldpass = (String) info.get("oldpass");
        String pass = (String) info.get("pass");
        Integer userid = (Integer) info.get("userid");
        if (userService.updateUserPassword(userid,oldpass, pass)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
