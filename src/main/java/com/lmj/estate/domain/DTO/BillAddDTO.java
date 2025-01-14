package com.lmj.estate.domain.DTO;

import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description 添加账单
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 19:15:54
 */
@Data
public class BillAddDTO {
    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 栋
     */
    @NotBlank(message = "栋不能为空")
    private String building;

    /**
     * 单元
     */
    @NotBlank(message = "单元不能为空")
    private String unit;

    /**
     * 门牌号
     */
    @NotBlank(message = "门牌号不能为空")
    private String number;

    /**
     * 费用名称
     */
    @NotBlank(message = "费用名称不能为空")
    private String amountName;

    /**
     * 费用价格
     */
    @NotBlank(message = "费用价格不能为空")
    private Double price;

    /**
     * 费用详情
     */
    private String costDetails;
    /**
     * 账单状态
     */
    private BillPaymentStatus status = BillPaymentStatus.PAYMENT_NON;
}
