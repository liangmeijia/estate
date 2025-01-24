package com.lmj.estate.domain.DTO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/24 16:48:40
 */
@Data
public class ComplaintAddDTO {
    /**
     * 申请人id
     */
    @NotNull(message = "申请人id不能为空")
    private Long applicantId;
    /**
     * 投诉申请事由
     */
    @NotEmpty(message = "投诉申请事由不能为空")
    private String reason;

}
