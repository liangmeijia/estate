package com.lmj.estate.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.BalancePaymentMethod;
import com.lmj.estate.domain.enums.BalancePaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 分页查询充值记录的结果
 * @date 2025/01/13 17:48:23
 */
@Data
public class BalanceRecordVO {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 充值金额
     */
    private Double amount;

    /**
     * 充值日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /**
     * 充值方式（0-微信；1-支付宝；2-银行卡）
     */
    private BalancePaymentMethod method;

    /**
     * 充值状态(0-充值成功；1-充值失败)
     */
    private BalancePaymentStatus status;
}
