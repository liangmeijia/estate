package com.lmj.estate.domain.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/21 23:54:17
 */
@Data
public class RepairUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    /**
     * 申请人id
     */
    @NotEmpty(message = "申请人id不能为空")
    private String applicantId;
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
