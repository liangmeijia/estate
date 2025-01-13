package com.lmj.estate.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.ParkStatus;
import com.lmj.estate.domain.enums.ParkType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 车位分页查询结果
 * @date 2025/01/13 15:31:40
 */
@Data
public class ParkVO {
    private Long id;
    /**
     * 车位位置
     */
    private String position;

    /**
     * 车位类型（0-标准车位；1-微型车位；2-大车位）
     */
    private ParkType type;

    /**
     * 车位价格
     */
    private Double price;

    /**
     * 车位状态（0-未使用；1-使用中）
     */
    private ParkStatus status;

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
