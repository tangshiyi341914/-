package com.project.controller;


import com.project.model.RespBean;
import com.project.services.impl.TOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@RestController
@RequestMapping("/tOrder")
@Api(value = "订单的控制器")
public class TOrderController {
    @Autowired
    private TOrderServiceImpl tOrderService;

    @ApiOperation("传入要购买的订单编号:no，和买家的账号:id")
    @PostMapping("/buy")
    public RespBean buy(@RequestBody Map<String, Object> map){
        int no= (int) map.get("no");
        int id= (int) map.get("id");

        //这个信息应该被存入order表
        return RespBean.ok("这里是 order");
    }
}

