package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSex {
    WOMAN(0,"女"),
    MAN(1,"男");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;

    //根据desc获取枚举类型
    public static UserSex fromDesc(String desc) {
        for (UserSex type : UserSex.values()) {
            if (type.getDesc().equals(desc)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with desc: " + desc);
    }
}
