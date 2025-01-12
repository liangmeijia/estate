package com.lmj.estate.domain.DTO;

import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.enums.UserSex;
import com.lmj.estate.domain.enums.UserStatus;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private UserSex sex;
    private String phone;
    private Double balance;
    private UserRole roleId;
    private UserStatus status;
}
