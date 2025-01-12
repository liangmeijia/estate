package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    NORMAL(0,"正常"),
    FREEZE(1,"冻结");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;

    public static UserStatus fromDesc(String desc) {
        for (UserStatus type : UserStatus.values()) {
            if (type.getDesc().equals(desc)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with desc: " + desc);
    }
}
