package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN(0,"管理员"),
    USER(1,"业主");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;
}
