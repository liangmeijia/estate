package com.lmj.estate.controller;

import com.lmj.estate.domain.DTO.UserAddDTO;
import com.lmj.estate.domain.DTO.UserRegisterDTO;
import com.lmj.estate.domain.VO.UserLoginVO;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping( "/loginOut")
    public R<String> loginOut(@RequestHeader String Token){
        return R.ok("退出成功");
    }

    /**
     * 注册
     * @param userRegisterDTO 注册信息
     * @return 水是否成功
     */
    @PostMapping("/register")
    public R<Void> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return userService.register(userRegisterDTO);
    }
}
