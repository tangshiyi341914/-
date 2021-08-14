package com.project.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.model.Listing;
import com.project.model.TOrder;
import com.project.mapper.TOrderMapper;
import com.project.services.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    TOrderMapper tOrderMapper;
    public List<TOrder> getNoByUser(Integer user){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper();
        queryWrapper.eq("buyerId",user).or().eq("sellerId",user);
        return tOrderMapper.selectList(queryWrapper);
    }
    public int insert(int no, int id1,int id2){
        TOrder tOrder = new TOrder();
//        tOrder.setNo();
        tOrder.setBuyerid(id1);
        tOrder.setSellerid(id2);
        tOrder.setEndtime(new Date());
        tOrder.setStarttime(new Date());
        tOrder.setListid(no);
        return tOrderMapper.insert(tOrder);
    }
}
