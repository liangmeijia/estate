package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 投诉状态
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 21:41:02
 */
@Getter
@AllArgsConstructor
public enum ComplaintsStatus {
    PENDING(0,"待处理"),
    PROCESSED(1,"已处理");
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
