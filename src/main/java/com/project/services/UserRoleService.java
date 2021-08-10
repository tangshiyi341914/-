package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.project.mapper.RoleMapper;
import com.project.mapper.UserMapper;
import com.project.mapper.UserRoleMapper;
import com.project.model.RespPageBean;
import com.project.model.Role;
import com.project.model.User;
import com.project.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sweary
 * @packagename:com.project.services
 * @classname:UserRoleService
 * @date 2021/8/7 - 21:23
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
@Service
public class UserRoleService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    public RespPageBean getAllUserRoles(Integer page, Integer size, User user) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<User> data = userRoleMapper.getAllUserRoles(page, size, user);
        for (int i = 0; i < data.size(); i++) {
            QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid", data.get(i).getId());
            UserRole userRole = userRoleMapper.selectOne(queryWrapper);
            if (userRole != null) {
                data.get(i).setRole(roleMapper.selectById(userRole.getRid()).getNameZh());
            } else {
                data.get(i).setRole("");
            }
        }
        Long total = userRoleMapper.getTotal(user);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        System.out.println(bean.getData());
        return bean;
    }

    public Integer deleteUserRoleById(Integer id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", id);
        return userRoleMapper.delete(queryWrapper);
    }

    public boolean updateUserRole(User user) {
        QueryWrapper<UserRole> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid", user.getId());
        UserRole userRole = userRoleMapper.selectOne(queryWrapper1);
        int result = 0;
        if (userRole != null) {
            UpdateWrapper<UserRole> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("uid", user.getId());
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("nameZh", user.getRole());
            Role role = roleMapper.selectOne(queryWrapper);
            userRole.setRid(role.getId());
            result = userRoleMapper.update(userRole, updateWrapper);
        } else {
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("nameZh", user.getRole());
            Role role = roleMapper.selectOne(queryWrapper);
            result = userRoleMapper.insert(new UserRole(user.getId(), role.getId()));
        }
        if (result == 1) {
            return true;
        }
        return false;
    }
}

