/**
 * -*- codeing = utf-8 -*-
 *
 * @Time :2021/8/3 15:10
 * @Author :zhou_pig
 * @File :MyMetaObiectHandle.java
 * @Software :IntelliJ IDEA
 */
package com.project.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandle implements MetaObjectHandler {

    //    插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("startTime", new Date(), metaObject);
    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("endTime", new Date(), metaObject);
    }
}