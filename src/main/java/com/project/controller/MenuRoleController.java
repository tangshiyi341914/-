package com.project.controller;


import com.project.model.Menu;
import com.project.model.RespBean;
import com.project.model.RespPageBean;
import com.project.services.MenuRoleService;
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
@RequestMapping("/user/menurole")
public class MenuRoleController {
    @Autowired
    MenuRoleService menuRoleService;
    @RequestMapping("/")
    public RespPageBean getAllMenusWithRole(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Menu menu)
    {
        return menuRoleService.getAllMenusWithRole(page, size, menu);
    }
    @DeleteMapping("/{id}")
    public RespBean deleteMenuRoleById(@PathVariable Integer id) {
        if (menuRoleService.deleteMenuRoleById(id) == 1 ) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @PutMapping("/")
    public RespBean updateMenuRole(@RequestBody Menu menu) {
        if (menuRoleService.updateMenuRole(menu)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}

