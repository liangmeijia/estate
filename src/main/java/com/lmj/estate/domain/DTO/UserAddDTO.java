package com.lmj.estate.domain.DTO;

import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.enums.UserSex;
import com.lmj.estate.domain.enums.UserStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserAddDTO {
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    private String password;
    private Integer age;
    private UserSex sex;
    @NotBlank(message = "电话不能为空")
    private String phone;
    private String email;
    private Double balance;
    @NotNull(message = "角色不能为空")
    private UserRole roleId;
    @NotNull(message = "状态不能为空")
    private UserStatus status;
}
