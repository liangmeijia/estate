package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    FREEZE(1,"冻结"),
    NORMAL(0,"正常");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;
}
