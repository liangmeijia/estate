package com.lmj.estate.interceptor;


import com.alibaba.fastjson.JSON;
import com.lmj.estate.config.AuthProperties;
import com.lmj.estate.domain.common.R;
import com.lmj.estate.entity.User;
import com.lmj.estate.service.UserService;
import com.lmj.estate.utils.JwtUtils;
import com.lmj.estate.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;
    private final UserContext userContext;
    private final AuthProperties authProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //0.请求是一个预检请求直接放行
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
        //1.获取当前路径
        String path = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        path = path.split(contextPath)[1];
        log.debug("当前路径："+path);
        //2.属于登录白名单就直接放行
        if(isExclude(path)){
            return true;
        }
        //3.获取请求头中的jwt token
        String jwtToken = request.getHeader("Token");
        try {
            //4.解析jwt token 并验证用户身份
            String userId = JwtUtils.parseJwtToken(jwtToken);
            log.debug("Parsed userId: " + userId);
            User user = userService.getById(userId);
            userContext.addUser(user);
        }catch (Exception e){
            //5.未登录就拦截
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(R.no("未登录哦!")));
            return false;
        }
        return true;
    }

    private boolean isExclude(String path) {
        for (String pattern:authProperties.getExcludePaths()){
            if(antPathMatcher.match(pattern,path)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userContext.clear();
    }
}
