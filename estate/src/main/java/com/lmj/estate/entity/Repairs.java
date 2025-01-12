package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Repairs implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 申请人id
     */
    private Integer applicantId;

    /**
     * 申请人姓名
     */
    private String applicantName;

    /**
     * 申请人电话
     */
    private String applicantPhone;

    /**
     * 申请人地址
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
     * 维修状态（1-待维修；2-维修中；3-维修完成）
     */
    private Byte status;

    /**
     * 维修开始日期
     */
    private LocalDateTime startTime;

    /**
     * 维修截至日期
     */
    private LocalDateTime endTime;

    /**
     * 维修费用
     */
    private BigDecimal price;

    /**
     * 维修账单id
     */
    private Integer billId;

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

    /**
     * 维修申请事由
     */
    private String reason;

    private static final long serialVersionUID = 1L;
}