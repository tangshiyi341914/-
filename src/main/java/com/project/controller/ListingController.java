package com.project.controller;


import com.project.model.Listing;
import com.project.model.RespBean;
import com.project.services.impl.ListingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@RestController
@RequestMapping("/list")
@Api("订单的控制器")
public class ListingController {
    @Autowired
    private ListingServiceImpl listingService;

    @GetMapping("/all")
    @ApiOperation("返回所有订单信息")
    public RespBean getList() {
//        listService.
        List<Listing> list = listingService.selectList();
        System.out.println(list);
        return RespBean.ok("已经获取到所有订单信息！", list);
    }

    @ApiOperation("current:当前页，" +
            "size:一页显示的数据数量(-1表示查询全部)" +
            "identity:0是买家发布的单子，1是卖家发布的单子。" +
            "使用map传入")
    @PostMapping("/page")
    public RespBean getPage(@RequestBody Map<String, Object> map) {
        System.out.println("getPage begin");
        int current = (int) map.get("current");
        int size = (int) map.get("size");
        int identity = (int) map.get("identity");

        return RespBean.ok("已经获取到指定订单信息！", listingService.selectListPage(current, size, identity));
    }
}

