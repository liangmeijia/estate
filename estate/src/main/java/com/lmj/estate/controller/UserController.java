package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserDTO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.User;
import com.lmj.estate.entity.UserRole;
import com.lmj.estate.entity.UserStatus;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 新增用户
     * @param userDTO 用户信息
     * @return
     */
    @PostMapping("/user")
    public R<Void> addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return R.ok();
    }

    /**
     * 查询用户
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/user/{id}")
    public R<UserVO> getUser(@PathVariable("id") long id){
        return R.ok(userService.findUserById(id));
    }

    /**
     * 查询用户列表
     * @param ids 用户ids
     * @return 用户列表
     */
    @GetMapping("/users")
    public R<List<User>> getUsers(@RequestParam("ids") List<Long> ids){
        return R.ok(userService.listByIds(ids));
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public R<Void> delUser(@PathVariable long id){
        userService.removeById(id);
        return R.ok();
    }

    /**
     * 修改用户
     * @param userDTO 用户信息
     * @return
     */
    @PutMapping("/user")
    public R<Void> updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return R.ok();
    }

    /**
     * 修改用户年龄
     * @param id 用户id
     * @param age 需减掉的年龄
     * @return
     */
    @PutMapping("/user/{id}/deduction/{age}")
    public R<Void> deductionAge(@PathVariable long id,@PathVariable int age){
        userService.deductionAge(id,age);
        return R.ok();
    }

//    @GetMapping("/user/page")
//    public R<PageDTO<UserVO>> getUsersPage(@RequestParam Long pageNum,
//                                           @RequestParam Long pageSize,
//                                           @RequestParam(value = "name",required = false) String name,
//                                           @RequestParam(value = "status",required = false) UserStatus status,
//                                           @RequestParam(value = "roleId",required = false) UserRole roleId){
//        return R.ok(userService.findUsersPage(pageNum,pageSize,name,status,roleId));
//    }

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
     * @return
     */
    @PostMapping("/users/import")
    public R<String> importUsers(@RequestParam("file") MultipartFile file) {
        return userService.importUsers(file);
    }

    /**
     * 批量导出用户信息
     * @param userQuery 查询条件
     * @return 导出结果
     */
    @PostMapping("/users/export")
    public void exportUsers(@RequestBody UserQuery userQuery,
                            HttpServletResponse response) {
        userService.exportUsers(userQuery, response);
    }
}
