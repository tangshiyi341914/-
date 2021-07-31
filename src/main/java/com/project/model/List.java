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
@ApiModel(value="List对象", description="")
public class List implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "挂牌者身份")
    private Integer identity;

    @ApiModelProperty(value = "挂牌单位")
    private String cpnName;

    @ApiModelProperty(value = "单据编号")
    private String no;

    @ApiModelProperty(value = "申请人")
    private String proposer ;

    @ApiModelProperty(value = "签发人")
    private String signer;

    @ApiModelProperty(value = "申请日期")
    private Date startTime;

    @ApiModelProperty(value = "交货时间-起始")
    private Date shipStartTime;

    @ApiModelProperty(value = "交货时间-结束")
    private Date shipEndTime;

    @ApiModelProperty(value = "煤种")
    private String coalType;

    @ApiModelProperty(value = "采购数量")
    private Double amount;

    @ApiModelProperty(value = "运输方式")
    private String transportation;

    @ApiModelProperty(value = "交货地点")
    private String deliveryPlace;

    @ApiModelProperty(value = "结算方式")
    private String clearing;

    @ApiModelProperty(value = "验收方式")
    private String acceptance;

    @ApiModelProperty(value = "结算付款方式")
    private String payment;

    @ApiModelProperty(value = "是否要求缴纳保证金")
    private Integer isPayDeposit;

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
    private Double ST;

    @ApiModelProperty(value = "哈氏可磨系数（HG）")
    private Double HG;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "状态")
    private Integer status ;


    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getProposer () {
        return proposer ;
    }

    public void setProposer (String proposer ) {
        this.proposer  = proposer ;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getShipStartTime() {
        return shipStartTime;
    }

    public void setShipStartTime(Date shipStartTime) {
        this.shipStartTime = shipStartTime;
    }

    public Date getShipEndTime() {
        return shipEndTime;
    }

    public void setShipEndTime(Date shipEndTime) {
        this.shipEndTime = shipEndTime;
    }

    public String getCoalType() {
        return coalType;
    }

    public void setCoalType(String coalType) {
        this.coalType = coalType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getClearing() {
        return clearing;
    }

    public void setClearing(String clearing) {
        this.clearing = clearing;
    }

    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getIsPayDeposit() {
        return isPayDeposit;
    }

    public void setIsPayDeposit(Integer isPayDeposit) {
        this.isPayDeposit = isPayDeposit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQnetar() {
        return qnetar;
    }

    public void setQnetar(Double qnetar) {
        this.qnetar = qnetar;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getM() {
        return m;
    }

    public void setM(Double m) {
        this.m = m;
    }

    public Double getRba() {
        return rba;
    }

    public void setRba(Double rba) {
        this.rba = rba;
    }

    public Double getLrbvc() {
        return lrbvc;
    }

    public void setLrbvc(Double lrbvc) {
        this.lrbvc = lrbvc;
    }

    public Double getHrbvc() {
        return hrbvc;
    }

    public void setHrbvc(Double hrbvc) {
        this.hrbvc = hrbvc;
    }

    public Double getMad() {
        return mad;
    }

    public void setMad(Double mad) {
        this.mad = mad;
    }

    public Double getStad() {
        return stad;
    }

    public void setStad(Double stad) {
        this.stad = stad;
    }

    public Double getLadbv() {
        return ladbv;
    }

    public void setLadbv(Double ladbv) {
        this.ladbv = ladbv;
    }

    public Double getHadbv() {
        return hadbv;
    }

    public void setHadbv(Double hadbv) {
        this.hadbv = hadbv;
    }

    public Double getQgrd() {
        return qgrd;
    }

    public void setQgrd(Double qgrd) {
        this.qgrd = qgrd;
    }

    public Double getStd() {
        return std;
    }

    public void setStd(Double std) {
        this.std = std;
    }

    public Double getLdwgbv() {
        return ldwgbv;
    }

    public void setLdwgbv(Double ldwgbv) {
        this.ldwgbv = ldwgbv;
    }

    public Double getHdwgbv() {
        return hdwgbv;
    }

    public void setHdwgbv(Double hdwgbv) {
        this.hdwgbv = hdwgbv;
    }

    public Double getGranularity() {
        return granularity;
    }

    public void setGranularity(Double granularity) {
        this.granularity = granularity;
    }

    public Double getST() {
        return ST;
    }

    public void setST(Double ST) {
        this.ST = ST;
    }

    public Double getHG() {
        return HG;
    }

    public void setHG(Double HG) {
        this.HG = HG;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus () {
        return status ;
    }

    public void setStatus (Integer status ) {
        this.status  = status ;
    }

    @Override
    public String toString() {
        return "List{" +
        "identity=" + identity +
        ", cpnName=" + cpnName +
        ", no=" + no +
        ", proposer =" + proposer  +
        ", signer=" + signer +
        ", startTime=" + startTime +
        ", shipStartTime=" + shipStartTime +
        ", shipEndTime=" + shipEndTime +
        ", coalType=" + coalType +
        ", amount=" + amount +
        ", transportation=" + transportation +
        ", deliveryPlace=" + deliveryPlace +
        ", clearing=" + clearing +
        ", acceptance=" + acceptance +
        ", payment=" + payment +
        ", isPayDeposit=" + isPayDeposit +
        ", price=" + price +
        ", qnetar=" + qnetar +
        ", start=" + start +
        ", m=" + m +
        ", rba=" + rba +
        ", lrbvc=" + lrbvc +
        ", hrbvc=" + hrbvc +
        ", mad=" + mad +
        ", stad=" + stad +
        ", ladbv=" + ladbv +
        ", hadbv=" + hadbv +
        ", qgrd=" + qgrd +
        ", std=" + std +
        ", ldwgbv=" + ldwgbv +
        ", hdwgbv=" + hdwgbv +
        ", granularity=" + granularity +
        ", ST=" + ST +
        ", HG=" + HG +
        ", comment=" + comment +
        ", status =" + status  +
        "}";
    }
}
