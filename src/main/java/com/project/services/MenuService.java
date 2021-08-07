package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.mapper.MenuMapper;
import com.project.mapper.MenuRoleMapper;
import com.project.mapper.RoleMapper;
import com.project.mapper.UserRoleMapper;
import com.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@CacheConfig(cacheNames = "menus_cache")
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
        List<Menu> menuChidren = menuMapper.selectBatchIds(ids);
        List<Integer> parentIds = new ArrayList<>();
        for (int i = 0; i < menuChidren.size(); i++) {
            parentIds.add(menuChidren.get(i).getParentId());
        }
        List<Menu> menuParent = menuMapper.selectBatchIds(parentIds);
        for (int i1 = 0; i1 < menuParent.size(); i1++) {
            List<Menu> each = new ArrayList<>();
            for (int i = 0; i < menuChidren.size(); i++) {
                if (Objects.equals(menuParent.get(i1).getId(), menuChidren.get(i).getParentId())) {
                    each.add(menuChidren.get(i));
                }
            }
            menuParent.get(i1).setChildren(each);
        }
        return menuParent;
    }

    @Cacheable
    public List<Menu> getAllMenusWithRole() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("id");
        List<Menu> list = menuMapper.selectList(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            QueryWrapper<MenuRole> queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("mid", list.get(i).getId());
            List<MenuRole> list1 = menuRoleMapper.selectList(queryWrapper1);
            List<Role> list2=new ArrayList<>();
            for (int i1 = 0; i1 < list1.size(); i1++) {
               list2.add(roleMapper.selectById(list1.get(i1).getRid()));
            }
            list.get(i).setRoles(list2);
        }
        return list;
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

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        QueryWrapper<MenuRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", rid);
        menuRoleMapper.delete(queryWrapper);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result = 0;
        for (int i = 0; i < mids.length; i++) {
            if (menuRoleMapper.insert(new MenuRole(rid, mids[i])) == 1) {
                result++;
            }
        }

        return result == mids.length;
    }
}
