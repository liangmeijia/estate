package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

@Data
@TableName(value = "bill_records",autoResultMap = true)
public class BillRecords implements Serializable {
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
     * 费用名称
     */
    private String amountName;

    /**
     * 缴费金额
     */
    private Double amount;

    /**
     * 缴费日期
     */
    private LocalDateTime date;

    /**
     * 缴费状态（0-代缴费；1-缴费成功；2-缴费失败）
     */
    private BillPaymentStatus status;

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