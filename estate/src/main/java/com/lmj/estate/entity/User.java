package com.lmj.estate.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "user",autoResultMap = true)
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    private String password;
    private Integer age;
    private UserSex sex;
    private String phone;
    private String email;
    private Double balance;
    private UserRole roleId;
    private UserStatus status;
    @TableLogic
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
