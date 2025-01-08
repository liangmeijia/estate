package com.lmj.estate.domain.VO;

import com.lmj.estate.entity.Menu;
import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserSex;
import com.lmj.estate.entity.UserStatus;
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
    private String address;
    private UserRole roleId;
    private UserStatus status;
    //菜单
    private List<Menu> menus;
}
