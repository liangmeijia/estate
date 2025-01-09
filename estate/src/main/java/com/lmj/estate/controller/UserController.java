package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.PageDTO;
import com.lmj.estate.domain.DTO.UserDTO;
import com.lmj.estate.domain.VO.UserVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.domain.query.UserQuery;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public R<Boolean> addUser(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        BeanUtils.copyProperties(userDTO,user);
        userService.save(user);
        return R.ok();
    }
    @GetMapping("/user/{id}")
    public R<UserVO> getUser(@PathVariable("id") long id){
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return R.ok(userVO);
    }
    @GetMapping("/users")
    public R<List<User>> getUsers(@RequestParam("ids") List<Long> ids){
        return R.ok(userService.listByIds(ids));
    }

    @DeleteMapping("/user/{id}")
    public R<Boolean> delUser(@PathVariable long id){
        userService.removeById(id);
        return R.ok();
    }
    @PutMapping("/user")
    public R<Boolean> updateUserById(@RequestBody UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        userService.updateById(user);
        return R.ok();
    }
    @PutMapping("/user/{id}/deduction/{age}")
    public R<Void> deductionAge(@PathVariable long id,@PathVariable int age){
        userService.deductionAge(id,age);
        return R.ok();
    }

    /**
     * 复杂条件【分页】查询
     * @param userQuery
     * @return
     */
    @PostMapping("/user/page")
    public R<PageDTO<UserVO>> getUsersPage(@RequestBody UserQuery userQuery){
        return R.ok(userService.findUsersPage(userQuery));
    }
}
