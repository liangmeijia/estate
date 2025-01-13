package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lmj.estate.domain.enums.ParkStatus;
import com.lmj.estate.domain.enums.ParkType;
import lombok.Data;

@Data
@TableName(value = "park",autoResultMap = true)
public class Park implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 车位位置
     */
    private String position;

    /**
     * 车位类型（0-标准车位；1-微型车位；2-大车位）
     */
    private ParkType type;

    /**
     * 车位价格
     */
    private Double price;

    /**
     * 车位状态（0-未使用；1-使用中）
     */
    private ParkStatus status;

    /**
     * 业主id
     */
    private Long userId;

    /**
     * 生效开始日期
     */
    private LocalDateTime startTime;

    /**
     * 生效截至日期
     */
    private LocalDateTime endTime;

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