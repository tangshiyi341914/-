package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.mapper.MenuMapper;
import com.project.mapper.MenuRoleMapper;
import com.project.mapper.RoleMapper;
import com.project.mapper.UserRoleMapper;
import com.project.model.Menu;
import com.project.model.MenuRole;
import com.project.model.User;
import com.project.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    public List<Menu> getMenusByUserId() {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        UserRole userRole = userRoleMapper.selectOne(queryWrapper);
        QueryWrapper<MenuRole> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("rid", userRole.getRid());
        List<MenuRole> menuRole = menuRoleMapper.selectList(queryWrapper1);
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < menuRole.size(); i++) {
            ids.add(menuRole.get(i).getMid());
        }
        List<Menu> menuChildren = menuMapper.selectBatchIds(ids);
        List<Integer> parentIds = new ArrayList<>();
        for (int i = 0; i < menuChildren.size(); i++) {
            parentIds.add(menuChildren.get(i).getParentId());
        }
        List<Menu> menuParent = menuMapper.selectBatchIds(parentIds);
        for (int i1 = 0; i1 < menuParent.size(); i1++) {
            List<Menu> each = new ArrayList<>();
            for (int i = 0; i < menuChildren.size(); i++) {
                if (Objects.equals(menuParent.get(i1).getId(), menuChildren.get(i).getParentId())) {
                    each.add(menuChildren.get(i));
                }
            }
            menuParent.get(i1).setChildren(each);
        }
        return menuParent;
    }
}
