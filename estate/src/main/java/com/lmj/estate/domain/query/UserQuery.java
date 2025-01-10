package com.lmj.estate.domain.query;

import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
public class UserQuery extends PageQuery{
    private String name;
    private UserStatus status;
    private UserRole roleId;
}
