package com.project.controller;


import com.project.model.Menu;
import com.project.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/user/rolemenu")
public class MenuRoleController {
    @Autowired
    MenuService menuService;
    @RequestMapping("/")
    public List<Menu> getAllMenusWithRole()
    {
        List<Menu> list =menuService.getAllMenusWithRole();
        System.out.println(list);
        return list;
    }
}

