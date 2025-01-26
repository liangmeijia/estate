package com.lmj.estate.domain.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lmj.estate.domain.enums.ComplaintsStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/26 10:37:08
 */
@Data
public class ProcessComplaintDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    /**
     * 处理结果
     */
    @NotEmpty(message = "处理结果不能为空")
    private String result;

}
