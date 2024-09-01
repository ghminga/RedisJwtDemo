package com.mym.service;

import com.mym.pojo.User;
import org.springframework.stereotype.Service;

public interface UserSerive {

    public User getUserById(String userId, String password);

    public String getToken();
}
