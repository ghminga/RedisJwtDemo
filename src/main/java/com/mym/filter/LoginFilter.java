package com.mym.filter;

import com.mym.until.RedisUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 登录过滤器
 */
public class LoginFilter {

    RedisUtils redisUtil = new RedisUtils();

    /**
     * Jwt验证
     * @param request
     * @return
     */
    public boolean executeLogin(ServletRequest request, ServletRespone response) {
        HttpServletRequest httpServletRequest = request;
        HttpServletResponse httpServletResponse = response;
        String token = httpServletRequest.getHeader(LOGIN_SIGN);

        JWTToken jwtToken = new JWTToken(token);
        try {
            // 向后传递JwtToken进行处理
            this.login(jwtToken);
        } catch (Exception e){
            // 抛出认证自定义异常
            return false;
        }
        return true;
    }

    /**
     * 解析token
     */
    private String login(String jwtToken) {
        // 校验是否命中
        if(ObjectUtils.isEmpty(redisUtil.getString(jwtToken))){
            return null;
        }
        // 解析token并返回登录信息
        return "返回登录信息";
    }


}
