package com.lmj.estate.domain.DTO;

import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * description 添加账单
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 19:15:54
 */
@Data
public class BillDTO {
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

    private BillPaymentStatus status = BillPaymentStatus.PAYMENT_NON;
}
