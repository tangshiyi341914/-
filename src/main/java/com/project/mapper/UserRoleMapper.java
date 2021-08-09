package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.model.User;
import com.project.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sweary
 * @packagename:com.project.mapper
 * @classname:UserRoleMapper
 * @date 2021/7/28 - 20:28
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<User> getAllUserRoles(@Param("page") Integer page, @Param("size") Integer size, @Param("user") User user);
    Long getTotal(@Param("user") User user);
}
