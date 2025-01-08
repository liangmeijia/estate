package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSex {
    MAN(1,"男"),
    WOMAN(0,"女");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;
}
