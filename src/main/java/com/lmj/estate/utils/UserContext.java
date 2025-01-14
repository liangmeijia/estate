package com.lmj.estate.utils;

import com.lmj.estate.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private ThreadLocal<User> threadLocal = new ThreadLocal<>();
    public void addUser(User user){
        threadLocal.set(user);
    }
    public User getUser(){
        return threadLocal.get();
    }
    public void clear(){
        threadLocal.remove();
    }
}
