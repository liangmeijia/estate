package com.lmj.estate.domain.query;

import com.lmj.estate.domain.enums.BillPaymentStatus;
import lombok.Data;

/**
 * description 账单查询
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 22:00:38
 */
@Data
public class BillQuery extends PageQuery{
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
     * 缴费状态（0-待缴费；1-缴费成功；2-缴费失败）
     */
    private BillPaymentStatus status;

}
