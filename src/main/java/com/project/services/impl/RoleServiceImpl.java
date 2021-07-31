package com.project.services.impl;

import com.project.model.Role;
import com.project.mapper.RoleMapper;
import com.project.services.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
