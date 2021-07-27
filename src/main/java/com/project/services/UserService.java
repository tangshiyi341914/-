package com.project.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.project.mapper.UserMapper;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        return user;
    }

//    public List<User> getAllUsers(User user) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper();
//        queryWrapper.s( user.getName() != null, "name", user.getName() );
//        return userMapper.selectList(queryWrapper);
//    }
    public boolean updateUserRole(String role, User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("username",user.getUsername());
        int result=userMapper.update(user,updateWrapper);
        if (result == 1) {
            return true;
        }
        return false;
    }

    public Integer deleteUserById(Integer id) {
        return userMapper.deleteById(id);
    }


    public boolean updateUserPasswd(User user ,String oldpass,String pass) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, user.getPassword())) {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("username",user.getUsername());
            String encodePass = encoder.encode(pass);
            user.setPassword(encodePass);
            Integer result = userMapper.update(user, updateWrapper);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }
}
