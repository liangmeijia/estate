package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.UserDTO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public R<UserLoginVO> login(@RequestBody UserDTO userDTO){
        return R.ok(userService.login(userDTO.getName(), userDTO.getPassword()));
    }

    @GetMapping(value = "/loginOut")
    public R<String> loginOut(@RequestHeader String Token){
        return R.ok("退出成功");
    }
}
