package com.lmj.estate.domain.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lmj.estate.domain.enums.ParkStatus;
import com.lmj.estate.domain.enums.ParkType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 车位复杂查询条件
 * @date 2025/01/13 15:31:05
 */
@Data
public class ParkQuery extends PageQuery{

    /**
     * 车位位置
     */
    private String position;

    /**
     * 车位类型（0-标准车位；1-微型车位；2-大车位）
     */
    private ParkType type;

    /**
     * 车位状态（0-未使用；1-使用中）
     */
    private ParkStatus status;

}
