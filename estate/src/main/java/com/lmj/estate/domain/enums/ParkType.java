package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 车位类型
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 21:43:38
 */
@Getter
@AllArgsConstructor
public enum ParkType {
    STANDARD(0,"标准车位"),
    MINI(1,"微型车位"),
    LARGE(2,"大车位");
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
