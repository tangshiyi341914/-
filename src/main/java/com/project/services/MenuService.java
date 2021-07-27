package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.mapper.MenuMapper;
import com.project.mapper.MenuRoleMapper;
import com.project.model.Menu;
import com.project.model.MenuRole;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    public List<Menu> getMenusByUserId() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid",((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        return menuMapper.selectList(queryWrapper);
    }

    @Cacheable
    public List<Menu> getAllMenusWithRole() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.ne("id",null);
        return menuMapper.selectList(queryWrapper);
    }

//    public List<Menu> getAllMenus() {
//        return menuMapper.getAllMenus();
//    }

    public List<Integer> getMidsByRid(Integer rid) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",rid);
        List<Menu> result=menuMapper.selectList(queryWrapper);
        List<Integer> list = new ArrayList<>(result.size());
        for (Menu menu : result) {
            list.add(menu.getId());
        }
        return list;
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        QueryWrapper<MenuRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid",rid);
        menuRoleMapper.delete(queryWrapper);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result=0;
        for (int i = 0; i < mids.length; i++) {
            if (menuRoleMapper.insert(new MenuRole(rid,mids[i]))==1){
                result++;
            }
        }

        return result==mids.length;
    }
}
