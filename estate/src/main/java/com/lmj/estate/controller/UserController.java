package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserAddDTO;
import com.lmj.estate.domain.DTO.UserUpdateDTO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BalancePaymentMethod;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @description 业主信息管理
 *
 * @author lmj
 * @version 1.0
 * @date 2025/01/10 22:37:41
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * 新增用户
     * @param userDTO 用户信息
     * @return 是否成功
     */
    @PostMapping("/user")
    public R<Void> addUser(@Valid @RequestBody UserAddDTO userDTO){
        return userService.addUser(userDTO);
    }

    /**
     * 查询用户
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/user/{id}")
    public R<UserVO> getUser(@PathVariable("id") long id){
        return R.ok(userService.findUserVOById(id));
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 是否成功
     */
    @DeleteMapping("/user/{id}")
    public R<Void> delUser(@PathVariable long id){
        if(userService.removeById(id)){
            return R.ok();
        }else {
            return R.no();
        }

    }

    /**
     * 修改用户
     * @param userDTO 用户信息
     * @return 是否成功
     */
    @PutMapping("/user")
    public R<Void> updateUser(@Valid @RequestBody UserUpdateDTO userDTO){
        return userService.updateUser(userDTO);
    }

    /**
     * 用户余额充值
     * @param id 用户id
     * @param balance 充值金额
     * @return 是否成功
     */
    @PutMapping("/balance/payment")
    public R<String> billPayment(@RequestParam long id, @RequestParam BalancePaymentMethod balancePaymentMethod, @RequestParam Double balance){
        return userService.increaseBalance(id,balancePaymentMethod,balance);
    }

    /**
     * 复杂条件【分页】查询
     * @param userQuery 查询条件
     * @return 分页结果
     */
    @PostMapping("/user/page")
    public R<PageDTO<UserVO>> getUsersPage(@RequestBody UserQuery userQuery){
        return R.ok(userService.findUsersPage(userQuery));
    }
    /**
     * 批量导入用户信息
     * @param file 用户信息文件
     * @return 是否成功
     */
    @PostMapping("/users/import")
    public R<String> importUsers(@RequestParam("file") MultipartFile file) {
        return userService.importUsers(file);
    }

    /**
     * 批量导出用户信息
     * @param userQuery 查询条件
     * @return 是否成功
     */
    @PostMapping("/users/export")
    public void exportUsers(@RequestBody UserQuery userQuery, HttpServletResponse response) {
        userService.exportUsers(userQuery, response);
    }
}
