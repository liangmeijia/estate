package com.lmj.estate.domain.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description 账单查询结果
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 18:56:19
 */
@Data
public class BillVO {
    private Integer id;
    private String address;
    private String building;
    private String unit;
    private String number;

    /**
     * 费用名称
     */
    private String amountName;

    /**
     * 费用价格
     */
    private Double price;

    /**
     * 费用详情
     */
    private String costDetails;

    /**
     * 缴费状态（0-待缴费；1-缴费成功；2-缴费失败）
     */
    private BillPaymentStatus status;
}
