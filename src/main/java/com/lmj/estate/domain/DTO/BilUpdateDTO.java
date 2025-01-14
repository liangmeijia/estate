package com.lmj.estate.domain.DTO;

import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description 修改账单
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 19:15:54
 */
@Data
public class BilUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    /**
     * 地址
     */
    private String address;

    /**
     * 栋
     */
    private String building;

    /**
     * 单元
     */
    private String unit;

    /**
     * 门牌号
     */
    private String number;

    /**
     * 费用名称
     */
    private String amountName;

    /**
     * 费用价格
     */
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
