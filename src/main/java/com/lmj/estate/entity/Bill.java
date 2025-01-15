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
@TableName(value = "bill",autoResultMap = true)
public class Bill implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 户主id
     */
    private Long userId;

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
     * 缴费状态（0-待缴费；1-缴费成功；2-缴费失败）
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