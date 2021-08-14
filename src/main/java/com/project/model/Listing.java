package com.project.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 基因重组
 * @since 2021-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="List对象", description="")
@TableName("list")
public class Listing implements Serializable {


    @ApiModelProperty(value = "挂牌者身份，0:买家  1:卖家")
    private Integer identity;

    @ApiModelProperty(value = "挂牌单位")
    @TableField("cpnName")
    private String cpnname;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "单据编号")
    private Integer no;

    @ApiModelProperty(value = "申请人")
    private Integer proposer;

    @ApiModelProperty(value = "签发人")
    private String signer;

    @ApiModelProperty(value = "申请日期")
    @TableField(fill = FieldFill.INSERT)
    private Date starttime;

    @ApiModelProperty(value = "交货时间-起始")
    @TableField(fill = FieldFill.UPDATE)
    private Date shipstarttime;

    @ApiModelProperty(value = "交货时间-结束")
    @TableField(fill = FieldFill.UPDATE)
    private Date shipendtime;

    @ApiModelProperty(value = "煤种")
    @TableField("coalType")
    private String coaltype;

    @ApiModelProperty(value = "采购数量")
    private Double amount;

    @ApiModelProperty(value = "运输方式")
    private String transportation;

    @ApiModelProperty(value = "交货地点")
    @TableField("deliveryPlace")
    private String deliveryplace;

    @ApiModelProperty(value = "结算方式")
    private String clearing;

    @ApiModelProperty(value = "验收方式")
    private String acceptance;

    @ApiModelProperty(value = "结算付款方式")
    private String payment;

    @ApiModelProperty(value = "是否要求缴纳保证金")
    @TableField("isPayDeposit")
    private Integer ispaydeposit;

    @ApiModelProperty(value = "保证金单价")
    private Double price;

    @ApiModelProperty(value = "收到基低位发热量Qnetar（KCal/kg）")
    private Double qnetar;

    @ApiModelProperty(value = "收到基全硫Start")
    private Double start;

    @ApiModelProperty(value = "全水分M")
    private Double m;

    @ApiModelProperty(value = "收到基灰分")
    private Double rba;

    @ApiModelProperty(value = "收到基挥发分最低")
    private Double lrbvc;

    @ApiModelProperty(value = "收到基挥发最高")
    private Double hrbvc;

    @ApiModelProperty(value = "空干基水分Mad")
    private Double mad;

    @ApiModelProperty(value = "空干基全硫Stad")
    private Double stad;

    @ApiModelProperty(value = "空干基挥发分最低")
    private Double ladbv;

    @ApiModelProperty(value = "空干基挥发分最高")
    private Double hadbv;

    @ApiModelProperty(value = "干基高位发热量qgrd")
    private Double qgrd;

    @ApiModelProperty(value = "干基全硫Std")
    private Double std;

    @ApiModelProperty(value = "干燥无灰色基挥发分最低")
    private Double ldwgbv;

    @ApiModelProperty(value = "干燥无灰基挥发分最高")
    private Double hdwgbv;

    @ApiModelProperty(value = "粒度（mm）")
    private Double granularity;

    @ApiModelProperty(value = "灰熔点ST（°C）")
    @TableField("ST")
    private Double st;

    @ApiModelProperty(value = "哈氏可磨系数（HG）")
    @TableField("HG")
    private Double hg;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "0是未审核\n" +
            "1是审核不通过\n" +
            "2是挂牌成功\n" +
            "3是交易完成")
    private Integer status;


}
