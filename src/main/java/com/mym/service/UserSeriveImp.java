package com.mym.service;

import com.mym.pojo.User;
import org.springframework.stereotype.Service;

@Service("UserSerive")
public class UserSeriveImp implements UserSerive{
    public User getUserById(String userId, String password) {
        return new User();
    }

    public String getToken() {
        return "这个是token";
    }
}
