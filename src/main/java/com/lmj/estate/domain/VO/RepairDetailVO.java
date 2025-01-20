package com.lmj.estate.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.RepairStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/20 16:59:36
 */
@Data
public class RepairDetailVO {
    private Long id;
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
     * 维修状态（0-待维修；1-维修中；2-维修完成）
     */
    private RepairStatus status;

    /**
     * 维修开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 维修截至日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 维修费用
     */
    private Double price;
    /**
     * 维修申请事由
     */
    private String reason;
}
