package com.lmj.estate.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 缴费记录查询条件
 * @date 2025/01/14 09:47:47
 */
@Data
public class BillRecordQuery extends PageQuery{
    /**
     * 当前用户id
     */
    private Long curUserId;
    /**
     * 地址
     */
    private String address;

    /**
     * 栋
     */
    private String building;

    /**
     * 单元
     */
    private String unit;

    /**
     * 门牌号
     */
    private String number;
    /**
     * 费用名称
     */
    private String amountName;
    /**
     * 缴费日期的开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 缴费日期的截至日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 缴费状态（1-缴费成功；2-缴费失败）
     */
    private BillPaymentStatus status;
}
