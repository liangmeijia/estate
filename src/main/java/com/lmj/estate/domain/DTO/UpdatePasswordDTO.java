package com.lmj.estate.domain.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author lmj
 * @version 1.0
 * @description
 * @date 2025/01/21 10:35:44
 */
@Data
public class UpdatePasswordDTO {
    @NotNull(message = "用户id不能为空")
    private Long userId;
    @NotEmpty(message = "原密码不能为空")
    private String password;
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
    @NotEmpty(message = "确认新密码不能为空")
    private String confirmNewPassword;
}
