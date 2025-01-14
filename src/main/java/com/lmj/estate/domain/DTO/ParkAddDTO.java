package com.lmj.estate.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.ParkStatus;
import com.lmj.estate.domain.enums.ParkType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 添加车位
 * @date 2025/01/13 14:35:55
 */
@Data
public class ParkAddDTO {
    /**
     * 车位位置
     */
    @NotBlank(message = "车位位置不能为空")
    private String position;

    /**
     * 车位类型（0-标准车位；1-微型车位；2-大车位）
     */
    @NotNull(message = "车位类型不能为空")
    private ParkType type;

    /**
     * 车位价格
     */
    @NotNull(message = "车位价格不能为空")
    private Double price;

    /**
     * 车位状态（0-未使用；1-使用中）
     */
    private ParkStatus status = ParkStatus.UNUSED;

    /**
     * 业主姓名
     */
    private String name;

    /**
     * 生效开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 生效截至日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
