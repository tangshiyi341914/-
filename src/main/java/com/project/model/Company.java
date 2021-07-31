package com.project.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Company对象", description="")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cpnId", type = IdType.AUTO)
    private Integer cpnId;

    private String cpnName;

    private String legalPerson;

    private String regstArea;

    private Integer regstCapital;

    private String cpnEmail;

    private String introduction;


    public Integer getCpnId() {
        return cpnId;
    }

    public void setCpnId(Integer cpnId) {
        this.cpnId = cpnId;
    }

    public String getCpnName() {
        return cpnName;
    }

    public void setCpnName(String cpnName) {
        this.cpnName = cpnName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getRegstArea() {
        return regstArea;
    }

    public void setRegstArea(String regstArea) {
        this.regstArea = regstArea;
    }

    public Integer getRegstCapital() {
        return regstCapital;
    }

    public void setRegstCapital(Integer regstCapital) {
        this.regstCapital = regstCapital;
    }

    public String getCpnEmail() {
        return cpnEmail;
    }

    public void setCpnEmail(String cpnEmail) {
        this.cpnEmail = cpnEmail;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Company{" +
        "cpnId=" + cpnId +
        ", cpnName=" + cpnName +
        ", legalPerson=" + legalPerson +
        ", regstArea=" + regstArea +
        ", regstCapital=" + regstCapital +
        ", cpnEmail=" + cpnEmail +
        ", introduction=" + introduction +
        "}";
    }
}
