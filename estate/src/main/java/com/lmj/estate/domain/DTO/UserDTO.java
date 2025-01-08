package com.lmj.estate.domain.DTO;

import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserSex;
import com.lmj.estate.entity.UserStatus;
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
    private String address;
    private UserRole roleId;
    private UserStatus status;
}