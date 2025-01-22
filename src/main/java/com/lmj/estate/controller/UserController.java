package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.*;
import com.lmj.estate.domain.VO.BalanceRecordVO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.enums.BalancePaymentMethod;
import com.lmj.estate.domain.query.BalanceRecordQuery;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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
     * @param balancePaymentDTO 余额充值参数
     * @return 是否成功
     */
    @PostMapping("/balance/payment")
    public R<String> balancePayment(@RequestBody BalancePaymentDTO balancePaymentDTO){
        return userService.increaseBalance(balancePaymentDTO);
    }

    /**
     * 分页查询用户
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

    /**
     * 分页查询充值记录
     * @param balanceRecordQuery 查询条件
     * @return 充值记录
     */
    @PostMapping("/balanceRecords")
    public R<PageDTO<BalanceRecordVO>> getBalanceRecords(@RequestBody BalanceRecordQuery balanceRecordQuery){
        return R.ok(userService.getBalanceRecords(balanceRecordQuery));
    }

    /**
     * 重置密码
     * @param id 用户id
     * @return 初始化密码
     */
    @PutMapping("/user/{id}")
    public R<Void> resetPassword(@PathVariable Long id){
        return userService.resetPassword(id);
    }

    /**
     * 修改个人密码
     * @param updatePasswordDTO 修改条件
     * @return 是否成功
     */
    @PostMapping("/user/updatePassword")
    public R<Void> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO){
        return userService.updatePassword(updatePasswordDTO);
    }
}
