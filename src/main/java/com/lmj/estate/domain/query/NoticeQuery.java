package com.lmj.estate.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/26 11:06:31
 */
@Data
public class NoticeQuery extends PageQuery{
    private String title;
    /**
     * 创建时间的起始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
}
