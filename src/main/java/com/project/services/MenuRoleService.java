package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.mapper.MenuMapper;
import com.project.mapper.MenuRoleMapper;
import com.project.mapper.RoleMapper;
import com.project.mapper.UserRoleMapper;
import com.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sweary
 * @packagename:com.project.services
 * @classname:MenuRoleService
 * @date 2021/8/10 - 16:50
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
@Service
public class MenuRoleService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;

    public RespPageBean getAllMenusWithRole(Integer page, Integer size, Menu menu) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Menu> data = menuRoleMapper.getAllMenusWithRole(page, size, menu);
        data.remove(0);
        for (int i = 0; i < data.size(); i++) {
            QueryWrapper<MenuRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mid", data.get(i).getId());
            List<MenuRole> menuRole = menuRoleMapper.selectList(queryWrapper);
            List<String> list = new ArrayList<>();
            for (int j = 0; j < menuRole.size(); j++) {
                Role role = roleMapper.selectById(menuRole.get(j).getRid());
                list.add(role.getNameZh());
            }
            data.get(i).setRoles(list);
        }
        System.out.println(data);
        Long total = menuRoleMapper.getTotal(menu);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        System.out.println(bean.getData());
        return bean;
    }

    public List<Integer> getMidsByRid(Integer rid) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", rid);
        List<Menu> result = menuMapper.selectList(queryWrapper);
        List<Integer> list = new ArrayList<>(result.size());
        for (Menu menu : result) {
            list.add(menu.getId());
        }
        return list;
    }

    public Integer deleteMenuRoleById(Integer id) {
        QueryWrapper<MenuRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mid", id);
        return menuRoleMapper.delete(queryWrapper);
    }

    @Transactional
    public boolean updateMenuRole(Menu menu) {
        int flag = 0;
        for (int i = 0; i < menu.getRoles().size(); i++) {
            QueryWrapper<MenuRole> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("mid", menu.getId());
            if (flag == 0)
                menuRoleMapper.delete(queryWrapper1);
            flag = 1;
            QueryWrapper<Role> queryWrapper = new QueryWrapper();
            System.out.println(menu.getRoles().get(i));
            queryWrapper.eq("nameZh", menu.getRoles().get(i));
            Role role = roleMapper.selectOne(queryWrapper);
            menuRoleMapper.insert(new MenuRole(menu.getId(), role.getId()));
        }
        return flag == 1;
    }
}
