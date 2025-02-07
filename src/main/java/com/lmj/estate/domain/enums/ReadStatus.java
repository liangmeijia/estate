package com.lmj.estate.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description 公告阅读状态
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/12 18:27:52
 */
@Getter
@AllArgsConstructor
public enum ReadStatus {
    READ_NO(0,"未读"),
    READ_YES(1,"已读");
    @EnumValue
    private Integer value;
    @JsonValue
    private String desc;

    public static ReadStatus fromDesc(String desc) {
        for (ReadStatus type : ReadStatus.values()) {
            if (type.getDesc().equals(desc)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with desc: " + desc);
    }
}
