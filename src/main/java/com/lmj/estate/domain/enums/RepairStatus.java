package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 维修状态
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 21:52:34
 */
@Getter
@AllArgsConstructor
public enum RepairStatus {
    UNDER_REPAIR(0,"待维修"),
    IN_REPAIR(1,"维修中"),
    REPAIRED(2,"维修完成");
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
