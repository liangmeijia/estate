package com.lmj.estate.domain.VO;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.RepairStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/20 15:59:03
 */
@Data
public class RepairVO {
    private Long id;
    /**
     * 申请人id
     */
    private Long applicantId;
    /**
     * 申请人姓名
     */
    private String applicantName;

    /**
     * 申请人电话
     */
    private String applicantPhone;

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

}
