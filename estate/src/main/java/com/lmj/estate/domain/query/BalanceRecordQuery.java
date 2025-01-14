package com.lmj.estate.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 充值记录查询条件
 * @date 2025/01/13 17:48:06
 */
@Data
public class BalanceRecordQuery extends PageQuery{
    /**
     * 充值日期的开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 充值日期的截至日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
