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
        QueryWrapper<Listing> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", 2);
        if (identity == 2) {
            listMapper.selectPage(page, queryWrapper);
        } else {
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

    public Map<String, Object> select(Map<String, Object> map) {
        System.out.println(map);
        String coalType= (String) map.get("coaltype");
        int qmin= (int) map.get("qmin");
        int qmax= (int) map.get("qmax");
        int mmin= (int) map.get("mmin");
        int mmax= (int) map.get("mmax");
        int smin= (int) map.get("smin");
        int smax= (int) map.get("smax");
        int identity= (int) map.get("identity");
        int current= (int) map.get("current");
        int size= (int) map.get("size");
        QueryWrapper<Listing> queryWrapper = new QueryWrapper();
        if(!coalType.equals("全部")){
            queryWrapper.eq("coalType",coalType);
        }
        queryWrapper.eq("identity", identity);
        queryWrapper.ge("qnetar",qmin);
        queryWrapper.le("qnetar",qmax);
        queryWrapper.ge("m",mmin);
        queryWrapper.le("m",mmax);
        queryWrapper.ge("stad",smin);
        queryWrapper.le("stad",smax);
        //一些条件
        queryWrapper.eq("status", 2);
        Page<Listing> page = new Page<>(current, size);
        listMapper.selectPage(page,queryWrapper);
        Map<String, Object> result = new HashMap<>();
        System.out.println("total:"+page.getTotal());
        System.out.println(page.getRecords());
        result.put("total", page.getTotal());
        result.put("records", page.getRecords());
        return result;
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
