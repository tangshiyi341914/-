package com.project.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.model.Listing;
import com.project.mapper.ListingMapper;
import com.project.services.ListingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@Service
public class ListingServiceImpl extends ServiceImpl<ListingMapper, Listing> implements ListingService {
    @Autowired
    private ListingMapper listMapper;

    public List<Listing> selectList() {
        return listMapper.selectList(null);
    }

    public Map<String, Object> selectListPage(int current, int size, int identity) {
        Page<Listing> page = new Page<>(current, size);
        if (identity == 2) {
            listMapper.selectPage(page, null);
        } else {
            QueryWrapper<Listing> queryWrapper = new QueryWrapper();
            queryWrapper.eq("identity", identity);
            listMapper.selectPage(page, queryWrapper);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return map;
    }

    public int insert(Listing listing) {
        return listMapper.insert(listing);
    }

    public int setStatus(int id, int status) {
        Listing listing = listMapper.selectById(id);
        listing.setStatus(status);
        return listMapper.updateById(listing);
    }

    public int delete(int id) {
        return listMapper.deleteById(id);
    }

    public List<Listing> select(Map<String, Object> map) {
        QueryWrapper<Listing> queryWrapper = new QueryWrapper();
        //一些条件
        queryWrapper.eq("status", 2);
        return listMapper.selectList(queryWrapper);
    }
    public Map<String, Object> selectListStatus(int current, int size, int status) {
        Page<Listing> page = new Page<>(current, size);
        if (status == 4) {
            listMapper.selectPage(page, null);
        } else {
            QueryWrapper<Listing> queryWrapper = new QueryWrapper();
            queryWrapper.eq("status", status);
            listMapper.selectPage(page, queryWrapper);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return map;
    }
}
