package com.bj.dfmanager.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bj.dfmanager.entity.User;
import com.bj.dfmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token
        String token = request.getHeader("Token");
        if (StringUtils.isEmpty(token)) {
            // 请求头中没有，从参数中获取
            token = request.getParameter("Token");
        }

        // 执行认证
        if (StringUtils.isEmpty(token)) {
            log.error("无token，请重新登录");
            response.setStatus(401);
            throw new Exception("无token");
        }

        // 获取token中的userId
        User user;
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            // 根据userId查询用户
            user = userService.findById(Integer.parseInt(userId));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(401);
            throw new Exception("token验证失败");
        }
        if (user == null) {
            log.error("用户不存在");
            response.setStatus(401);
            throw new Exception("用户不存在");
        }
        if ("N".equals(user.getUserStatus())) {
            log.error("用户不可用");
            response.setStatus(401);
            throw new Exception("用户不可用");
        }

        // 以下为：同一个用户只能在一个地方登录
        if (!token.equals(user.getToken())) {
            log.error("token验证失败 ");
            response.setStatus(401);
            throw new Exception("token验证失败");
        }

        // 以下为：同一个用户可在不同地方登录
        /*try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            log.error("token验证失败，请重新登录");
            throw new Exception("token验证失败，请重新登录");
        }*/

        return true;
    }

}
