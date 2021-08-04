package com.project.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Company对象", description="")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cpnId", type = IdType.AUTO)
    private Integer cpnid;

    @TableField("cpnName")
    private String cpnname;

    @TableField("legalPerson")
    private String legalperson;

    @TableField("regstArea")
    private String regstarea;

    @TableField("regstCapital")
    private Integer regstcapital;

    @TableField("cpnEmail")
    private String cpnemail;

    private String introduction;


}
