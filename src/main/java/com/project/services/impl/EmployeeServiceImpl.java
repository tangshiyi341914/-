package com.project.services.impl;

import com.project.model.Employee;
import com.project.mapper.EmployeeMapper;
import com.project.services.EmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
