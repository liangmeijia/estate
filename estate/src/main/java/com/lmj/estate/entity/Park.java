package com.lmj.estate.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Park implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 车位位置
     */
    private String position;

    /**
     * 车位类型（1-标准车位；2-微型车位；3-大车位）
     */
    private Byte type;

    /**
     * 车位价格
     */
    private BigDecimal price;

    /**
     * 车位状态（1-未使用；2-使用中）
     */
    private Byte status;

    /**
     * 业主id
     */
    private Integer userId;

    /**
     * 生效开始日期
     */
    private Date startTime;

    /**
     * 生效截至日期
     */
    private Date endTime;

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