package com.lmj.estate.domain.VO;

import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserSex;
import com.lmj.estate.entity.UserStatus;
import lombok.Data;

@Data
public class UserVO {
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
