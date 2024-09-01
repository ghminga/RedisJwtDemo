package com.mym.controller;

import com.alibaba.fastjson.JSONObject;
import com.mym.pojo.User;
import com.mym.service.UserSerive;
import com.mym.until.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Redis作为登录缓存时的使用
 */
@Controller
public class LoginController {

    @Autowired
    UserSerive userSerive;

    RedisUtils redisUtil = new RedisUtils();

    /*
    登录方法
     */
    @RequestMapping("/mym/login")
    public String login() {
        String userid = "";
        String password = "";
        // 判断入参
        if(StringUtils.isEmpty(userid) && StringUtils.isEmpty(password)) {
            // 返回自定义异常
            return "code:-1 message:账号或密码为空";
        }

        // 判断账号
        User user = userSerive.getUserById(userid, password);
        if(ObjectUtils.isEmpty(user)) {
            // 返回自定义异常
            return "code:-2 message:用户不存在";
        }

        // 根据Jwt获取Token
        String token = userSerive.getToken();
        if(StringUtils.hasLength(token)) {
            // 存放token到Redis中
            redisUtil.setString(token, userid, 10000L);
            return token;
        }

        return "code:-3 message:登录异常，请联系管理员";
    }

}
