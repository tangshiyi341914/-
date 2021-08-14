package com.project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.mapper.ListingMapper;
import com.project.model.Listing;
import com.project.model.RespBean;
import com.project.model.TOrder;
import com.project.services.impl.ListingServiceImpl;
import com.project.services.impl.TOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ProjectApplicationTests {
    @Autowired
    private ListingMapper listingMapper;
    @Autowired
    private ListingServiceImpl listingService;
    @Autowired
    private TOrderServiceImpl tOrderService;
    @Test
    void contextLoads() {
        System.out.println(listingService.selectListPage(1, 10, 1));
    }

    @Test
    void selectTest() {
        String coalType= "全部";
        double qmin=2000;
        double qmax= 4500;
        double mmin=5;
        double mmax= 15;
        double smin= 4;
        double smax= 26;
        QueryWrapper<Listing> queryWrapper = new QueryWrapper();
        queryWrapper.ge("qnetar",qmin);
        queryWrapper.le("qnetar",qmax);
        queryWrapper.ge("m",mmin);
        queryWrapper.le("m",mmax);
        queryWrapper.ge("stad",smin);
        queryWrapper.le("stad",smax);
        //一些条件
        System.out.println("搜索结果如下：");
        queryWrapper.eq("status", 2);
        List<Listing> listings = listingMapper.selectList(queryWrapper);
        System.out.println(listings);
    }

    @Test
    void generateSomeApi() {
        Map<String, Object> map = new HashMap<>();
        map.put("identify", 1);
        map.put("current", 2);
        map.put("size", 6);
        System.out.println(map);
    }
    @Test
    void Test2(){
        int no=1,id1=1;
        listingService.setStatus(no, 3);//交易成功
        Integer id2 = listingService.getById(no).getProposer();
        //这个信息应该被存入order表
        if(tOrderService.insert(no,id1,id2)>0)
            System.out.println("successful");
        else
            System.out.println("failed");

    }
}
