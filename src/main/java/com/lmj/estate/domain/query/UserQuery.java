package com.lmj.estate.domain.query;

import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.enums.UserStatus;
import lombok.Data;

@Data
public class UserQuery extends PageQuery{
    private String name;
    private UserStatus status;
    private UserRole roleId;
}
