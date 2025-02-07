package com.lmj.estate.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.ReadStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/02/07 10:01:02
 */
@Data
public class NoticeDetailVO {
    private Long id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 状态（0-未读，1-已读）
     */
    private ReadStatus status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
