package com.lmj.estate.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Complaints implements Serializable {
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
     * 投诉状态（1-待处理；2-已处理）
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