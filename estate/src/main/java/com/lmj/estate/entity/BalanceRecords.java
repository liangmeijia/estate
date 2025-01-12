package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.lmj.estate.domain.enums.BalancePaymentMethod;
import com.lmj.estate.domain.enums.BalancePaymentStatus;
import lombok.Data;

@Data
@TableName(value = "balance_records",autoResultMap = true)
public class BalanceRecords implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 充值金额
     */
    private Double amount;

    /**
     * 充值日期
     */
    private LocalDateTime date;

    /**
     * 充值方式（0-微信；1-支付宝；2-银行卡）
     */
    private BalancePaymentMethod method;

    /**
     * 充值状态(0-充值成功；1-充值失败)
     */
    private BalancePaymentStatus status;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}