package com.lmj.estate.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lmj.estate.domain.DTO.BalancePaymentDTO;
import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserAddDTO;
import com.lmj.estate.domain.DTO.UserUpdateDTO;
import com.lmj.estate.domain.VO.BalanceRecordVO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BalancePaymentMethod;
import com.lmj.estate.domain.query.BalanceRecordQuery;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.User;
import com.lmj.estate.domain.enums.UserRole;
import com.lmj.estate.domain.enums.UserStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {
    R<String> increaseBalance(BalancePaymentDTO balancePaymentDTO);
    PageDTO<UserVO> findUsersPage(Long pageNum, Long pageSize, String name, UserStatus status, UserRole roleId);
    PageDTO<UserVO> findUsersPage(UserQuery userQuery);
    R<UserLoginVO> login(String userName, String password);

    R<Void> addUser(UserAddDTO userDTO);
    UserVO findUserVOById(long id);
    R<Void> updateUser(UserUpdateDTO userDTO);

    R<String> importUsers(MultipartFile file);
    void exportUsers(UserQuery userQuery, HttpServletResponse  response) ;

    PageDTO<BalanceRecordVO> getBalanceRecords(BalanceRecordQuery balanceRecordQuery);
}
