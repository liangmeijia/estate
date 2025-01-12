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
    private Long id;
    private String amountName;
    private BillPaymentStatus status;

}
