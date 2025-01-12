package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 余额充值状态
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 18:27:52
 */
@Getter
@AllArgsConstructor
public enum BalancePaymentStatus {
    PAYMENT_SUCCESS(0,"充值成功"),
    PAYMENT_FAILED(1,"充值失败");
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
