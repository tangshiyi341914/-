package com.project.model;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-03
 */
@ApiModel(value="T_order对象", description="")
public class T_order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String no;

    private Date startTime;

    private Date endTime;

    private Integer buyerId;

    private Integer sellerId;

    private String listId;


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "T_order{" +
        "no=" + no +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", buyerId=" + buyerId +
        ", sellerId=" + sellerId +
        ", listId=" + listId +
        "}";
    }
}
