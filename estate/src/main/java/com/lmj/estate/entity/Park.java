package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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