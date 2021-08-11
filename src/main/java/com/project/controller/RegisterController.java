package com.project.controller;

import com.project.model.Company;
import com.project.model.RespBean;
import com.project.model.User;
import com.project.services.UserRoleService;
import com.project.services.UserService;
import com.project.services.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    CompanyServiceImpl companyService;
    @Autowired
    UserRoleService userRoleService;
    @PostMapping("/registerUser")
    public RespBean registerUser(@RequestBody User user){
        int result=0;
        if(userService.addUser(user)==1)
        {
            if(userRoleService.addUserRole(user.getId())==1)
                result=1;
        }
        if(result==1) {
            return RespBean.ok("注册成功");
        }
        else {
            userService.deleteUserById(user.getId());
            return RespBean.error("注册失败，请检查是否已经注册过");
        }
    }
    @PostMapping("/registerCompany")
    public RespBean registerCompany(@RequestBody Company company){
        if(companyService.addCompany(company)==1)
            return RespBean.ok("注册成功");
        else {
            return RespBean.error("注册失败，请检查是否已经注册过");
        }
    }
}
