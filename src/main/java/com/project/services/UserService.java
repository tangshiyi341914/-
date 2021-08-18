package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.project.mapper.RoleMapper;
import com.project.mapper.UserMapper;
import com.project.mapper.UserRoleMapper;
import com.project.model.RespPageBean;
import com.project.model.User;
import com.project.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Sweary
 * @packagename:com.project.services
 * @classname:UserService
 * @date 2021/7/24 - 23:20
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        QueryWrapper<UserRole> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid", user.getId());
        user.setRole(roleMapper.selectById(userRoleMapper.selectOne(queryWrapper1).getRid()).getNameZh());
        return user;
    }

    public RespPageBean getAllUsers(Integer page, Integer size, User user, Date[] beginDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<User> data = userMapper.getAllUsers(page, size, user, beginDateScope);
        Long total = userMapper.getTotal(user, beginDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        System.out.println(bean.getData());
        return bean;
    }

    public boolean updateUserRole(Integer uid, Integer rid) {
        UpdateWrapper<UserRole> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("uid", uid);
        UserRole userRole = userRoleMapper.selectById(uid);
        userRole.setRid(rid);
        int result = userRoleMapper.update(userRole, updateWrapper);
        if (result == 1) {
            return true;
        }
        return false;
    }

    public Integer deleteUserById(Integer id) {
        QueryWrapper<UserRole> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uid",id);
        userRoleMapper.delete(queryWrapper);
        return userMapper.deleteById(id);
    }
    public Integer updateUser(User user) {
        return userMapper.updateById(user);
    }

    public boolean updateUserPassword(Integer userid, String oldpass, String pass) {
        User user = userMapper.selectById(userid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, user.getPassword())) {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("username", user.getUsername());
            String encodePass = encoder.encode(pass);
            user.setPassword(encodePass);
            Integer result = userMapper.update(user, updateWrapper);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }
    public int addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String newPwd=encoder.encode(user.getPassword());
        user.setPassword(newPwd);
        return userMapper.insert(user);
    }

    public int addUsers(List<User> users) {
        int count=0;
        for (int i = 0; i < users.size(); i++) {
            count+=userMapper.insert(users.get(i));
        }
        return count;
    }
}
