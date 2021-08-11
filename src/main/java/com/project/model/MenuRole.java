package com.project.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "menu_role")
public class MenuRole {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer mid;

    private Integer rid;
    @TableField(exist = false)
    private String  nameZh;
    @TableField(exist = false)
    private String  mName;

    public MenuRole(Integer mid, Integer rid) {
        this.mid=mid;this.rid=rid;
    }
}