package com.lmj.estate.domain.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/20 15:25:29
 */
@Data
public class RepairAddDTO {
    /**
     * 申请人姓名
     */
    @NotEmpty(message = "申请人姓名不能为空")
    private String applicantName;
    /**
     * 申请人电话
     */
    @NotEmpty(message = "申请人电话不能为空")
    private String applicantPhone;
    /**
     * 申请人地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;

    /**
     * 栋
     */
    @NotEmpty(message = "栋不能为空")
    private String building;

    /**
     * 单元
     */
    @NotEmpty(message = "单元不能为空")
    private String unit;

    /**
     * 门牌号
     */
    @NotEmpty(message = "门牌号不能为空")
    private String number;

    /**
     * 维修申请事由
     */
    @NotEmpty(message = "维修申请事由不能为空")
    private String reason;

}
