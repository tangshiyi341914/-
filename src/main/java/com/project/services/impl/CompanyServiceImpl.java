package com.project.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.model.Company;
import com.project.mapper.CompanyMapper;
import com.project.model.UserRole;
import com.project.services.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    public int addCompany(Company company) {
        int result=0;
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cpnName", company.getCpnname());
        Company company1 = companyMapper.selectOne(queryWrapper);
        if(company1==null){
            companyMapper.insert(company);
            result=1;
        }
        return result;
    }
}
