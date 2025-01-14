package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.UserAddDTO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    /**
     * 登录
     * @param form 登录参数（用户名、密码）
     * @return 是否成功
     */
    @PostMapping("/login")
    public R<UserLoginVO> login(@RequestBody Map<String, String> form){
        return userService.login(form.get("name"), form.get("password"));
    }

    /**
     * 登出
     * @param Token JWT Token
     * @return 是否成功
     */
    @GetMapping(value = "/loginOut")
    public R<String> loginOut(@RequestHeader String Token){
        return R.ok("退出成功");
    }
}
