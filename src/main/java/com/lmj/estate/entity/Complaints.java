package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.lmj.estate.domain.enums.ComplaintsStatus;
import lombok.Data;

@Data
@TableName(value = "complaints",autoResultMap = true)
public class Complaints implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请人id
     */
    private Long applicantId;

    /**
     * 投诉状态（0-待处理；1-已处理）
     */
    private ComplaintsStatus status;

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
     * 投诉申请事由
     */
    private String reason;

    /**
     * 处理结果
     */
    private String result;

    private static final long serialVersionUID = 1L;
}