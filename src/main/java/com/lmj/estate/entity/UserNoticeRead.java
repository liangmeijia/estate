package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.lmj.estate.domain.enums.ReadStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName(value = "user_notice_read",autoResultMap = true)
public class UserNoticeRead implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 公告id
     */
    private Long noticeId;

    /**
     * 状态（0-未读，1-已读）
     */
    private ReadStatus status;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public UserNoticeRead(Long userId, Long noticeId, ReadStatus status, LocalDateTime createTime) {
        this.userId = userId;
        this.noticeId = noticeId;
        this.status = status;
        this.createTime = createTime;
    }
}