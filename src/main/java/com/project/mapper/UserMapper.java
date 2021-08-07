package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Sweary
 * @packagename:com.project.mapper
 * @classname:UserMapper
 * @date 2021/7/23 - 19:34
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> getAllUsers(@Param("page") Integer page, @Param("size") Integer size, @Param("user") User user, @Param("beginDateScope") Date[] beginDateScope);

    Long getTotal(@Param("user") User user,@Param("beginDateScope") Date[] beginDateScope);
}
