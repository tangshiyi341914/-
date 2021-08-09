package com.project.controller;


import com.project.model.RespBean;
import com.project.model.RespPageBean;
import com.project.model.User;
import com.project.services.MenuService;
import com.project.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user/userrole")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    MenuService menuService;

    @DeleteMapping("/{id}")
    public RespBean deleteUserRoleById(@PathVariable Integer id) {
        if (userRoleService.deleteUserRoleById(id) == 1 ) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/")
    public RespBean updateUserRole(@RequestBody User user) {
        if (userRoleService.updateUserRole(user)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @RequestMapping("/")
    public RespPageBean getAllUserRoles(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, User user)
    {
        return userRoleService.getAllUserRoles(page, size, user);
    }
}

