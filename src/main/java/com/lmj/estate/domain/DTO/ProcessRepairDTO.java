package com.lmj.estate.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/20 15:43:21
 */
@Data
public class ProcessRepairDTO {
    /**
     * 维修申请id
     */
    @NotNull(message = "维修申请id不能为空")
    private Long id;
    /**
     * 维修开始日期
     */
    @NotNull(message = "维修开始日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 维修截至日期
     */
    @NotNull(message = "维修截至日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 维修费用
     */
    @NotNull(message = "维修费用不能为空")
    private Double price;
}
