package com.lmj.estate.domain.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lmj.estate.domain.enums.ComplaintsStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/24 16:59:04
 */
@Data
public class ComplaintVO {
    /**
     * 主键
     */
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
     * 投诉状态（0-待处理；1-已处理）
     */
    private ComplaintsStatus status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 投诉申请事由
     */
    private String reason;

    /**
     * 处理结果
     */
    private String result;

}
