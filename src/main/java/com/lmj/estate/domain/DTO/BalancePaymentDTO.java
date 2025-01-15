package com.lmj.estate.domain.DTO;

import com.lmj.estate.domain.enums.BalancePaymentMethod;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lmj
 * @version 1.0
 * @description 用户余额充值
 * @date 2025/01/15 16:32:44
 */
@Data
public class BalancePaymentDTO {
    /**
     * 充值人id
     */
    private Long userId;
    /**
     * 充值方式
     */
    private BalancePaymentMethod method;
    /**
     * 充值金额
     */
    private Double balance;
}
