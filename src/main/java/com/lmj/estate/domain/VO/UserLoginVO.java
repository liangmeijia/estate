package com.lmj.estate.domain.VO;

import com.lmj.estate.entity.Menu;
import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.enums.UserSex;
import com.lmj.estate.domain.enums.UserStatus;
import lombok.Data;

import java.util.List;

@Data
public class UserLoginVO {
    private String token;
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
    //菜单
    private List<Menu> menus;
}
