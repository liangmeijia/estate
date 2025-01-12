package com.lmj.estate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class BalanceRecords implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 充值金额
     */
    private BigDecimal amount;

    /**
     * 充值日期
     */
    private Date date;

    /**
     * 充值方式
     */
    private String method;

    /**
     * 充值状态(1-充值成功；2-充值失败；3-充值中)
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