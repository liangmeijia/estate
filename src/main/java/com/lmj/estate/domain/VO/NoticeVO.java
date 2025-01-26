package com.lmj.estate.domain.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 公告内容
     */
    private String content;

}
