package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 账单缴费状态
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 19:24:11
 */
@Getter
@AllArgsConstructor
public enum BillPaymentStatus {
    PAYMENT_NON(0,"代缴费"),
    PAYMENT_SUCCESS(1,"缴费成功"),
    PAYMENT_FAILED(2,"缴费失败");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;

    public static UserRole fromDesc(String desc) {
        for (UserRole type : UserRole.values()) {
            if (type.getDesc().equals(desc)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with desc: " + desc);
    }
}
