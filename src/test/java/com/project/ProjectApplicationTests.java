package com.project;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.mapper.ListingMapper;
import com.project.model.Listing;
import com.project.services.impl.ListingServiceImpl;
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
    @Test
    void contextLoads() {
        List<Listing> list = listingService.selectListPage(1, 10, 1);
        list.forEach(System.out::println);

    }
    @Test
    void selectTest(){
        Page<Listing> page = new Page<>(3, -1);
        listingMapper.selectPage(page, null);
//        List<Listing> list = listingMapper.selectList(listingPage);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    void generateSomeApi(){
        Map<String,Object> map=new HashMap<>();
        map.put("identify",1);
        map.put("current",2);
        map.put("size",6);
        System.out.println(map);
    }
}
