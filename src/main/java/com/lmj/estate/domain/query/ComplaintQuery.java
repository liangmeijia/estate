package com.lmj.estate.domain.query;

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
 * @date 2025/01/24 16:58:39
 */
@Data
public class ComplaintQuery extends PageQuery{
    /**
     * 当前用户id
     */
    private Long curUserId;
    /**
     * 投诉状态（0-待处理；1-已处理）
     */
    private ComplaintsStatus status;
}
