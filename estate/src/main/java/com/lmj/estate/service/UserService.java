package com.lmj.estate.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserDTO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.User;

public interface UserService extends IService<User> {
    void deductionAge(long id, int age);

    PageDTO<UserVO> findUsersPage(UserQuery userQuery);

    UserLoginVO login(String userName, String password);
}
