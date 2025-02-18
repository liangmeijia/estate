package com.lmj.estate.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.ReadStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/26 11:06:15
 */
@Data
public class NoticeVO {
    private Long id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告阅读状态
     */
    private ReadStatus status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
