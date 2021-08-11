package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.model.Menu;
import com.project.model.MenuRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    List<Menu> getAllMenusWithRole(@Param("page") Integer page, @Param("size") Integer size, @Param("menu") Menu menu);
    Long getTotal(@Param("menu") Menu menu);
}