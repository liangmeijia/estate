package com.lmj.estate.domain.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.RepairStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/20 15:56:27
 */
@Data
public class RepairQuery extends PageQuery{
    /**
     * 当前用户id
     */
    private Long curUserId;

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
