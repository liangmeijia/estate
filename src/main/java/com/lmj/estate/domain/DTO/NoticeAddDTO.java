package com.lmj.estate.domain.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/26 11:15:07
 */
@Data
public class NoticeAddDTO {
    /**
     * 公告标题
     */
    @NotEmpty(message = "公告标题不能为空")
    private String title;

    /**
     * 公告内容
     */
    private String content;

}
