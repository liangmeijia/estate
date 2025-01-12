package com.lmj.estate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class BillRecords implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 费用名称
     */
    private String amountName;

    /**
     * 缴费金额
     */
    private BigDecimal amount;

    /**
     * 缴费日期
     */
    private Date date;

    /**
     * 缴费状态（1-缴费成功；2-缴费失败；3-缴费中）
     */
    private Byte status;

    /**
     * 逻辑删除
     */
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}