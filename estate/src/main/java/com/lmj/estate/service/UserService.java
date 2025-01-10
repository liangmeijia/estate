package com.lmj.estate.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserDTO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.User;
import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface UserService extends IService<User> {
    void deductionAge(long id, int age);

    PageDTO<UserVO> findUsersPage(Long pageNum, Long pageSize, String name, UserStatus status, UserRole roleId);
    PageDTO<UserVO> findUsersPage(UserQuery userQuery);
    R<UserLoginVO> login(String userName, String password);

    void addUser(UserDTO userDTO);

    UserVO findUserById(long id);

    void updateUser(UserDTO userDTO);

    R<String> importUsers(MultipartFile file);
    void exportUsers(UserQuery userQuery, HttpServletResponse  response) ;

}
