package com.lmj.estate.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.ReadStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/26 11:06:31
 */
@Data
public class NoticeQuery extends PageQuery{
    @NotNull(message = "登录用户id不能为空")
    private Long userId;
    /**
     * 公告的阅读状态
     */
    private ReadStatus status;
    /**
     * 创建时间的起始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
}
