package com.lmj.estate.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lmj
 * @version 1.0
 * @description 分页查询缴费记录的结果
 * @date 2025/01/14 09:47:37
 */
@Data
public class BillRecordVO {
    private Long id;

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
     * 缴费金额
     */
    private Double amount;

    /**
     * 缴费日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /**
     * 缴费状态（1-缴费成功；2-缴费失败）
     */
    private BillPaymentStatus status;

    /**
     * 缴费人姓名
     */
    private String name;
    /**
     * 缴费人id
     */
    private Long userId;
}
