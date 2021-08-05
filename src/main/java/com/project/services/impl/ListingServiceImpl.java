package com.project.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.Listing;
import com.project.mapper.ListingMapper;
import com.project.services.ListingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@Service
public class ListingServiceImpl extends ServiceImpl<ListingMapper, Listing> implements ListingService {
    @Autowired
    private ListingMapper listMapper;
    public List<Listing> selectList(){
        return listMapper.selectList(null);
    }

    public List<Listing> selectListPage(int current, int size, int identity) {
        Page<Listing> page = new Page<>(current,size);
        QueryWrapper<Listing> queryWrapper = new QueryWrapper();
        queryWrapper.eq("identity",identity);
        listMapper.selectPage(page,queryWrapper);
        return page.getRecords();
    }
}
