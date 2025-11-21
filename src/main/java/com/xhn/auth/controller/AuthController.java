package com.xhn.auth.controller;

import com.xhn.auth.model.LoginRequest;
import com.xhn.auth.model.LoginResponseModel;
import com.xhn.base.ResponseResult;
import com.xhn.sys.model.UsersEntity;
import com.xhn.sys.service.IUsersService;
import com.xhn.security.utils.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器 - WebFlux 响应式
 *
 * @author xhn
 */
@RestController
@RequestMapping("/auth/account")
public class AuthController {

    private final IUsersService usersService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(IUsersService usersService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 初始化管理员用户接口
     */
    @PostMapping("/initAdminUser")
    public Mono<ResponseResult<String>> initAdminUser() {
        return Mono.fromCallable(() -> {
            boolean created = usersService.createAdminIfNotExists();
            return created ? ResponseResult.success("注册成功") : ResponseResult.failed(400, "用户名已存在");
        });

    }

    /**
     * 登录接口 - 返回 JWT token
     */
    @PostMapping("/login")
    public Mono<ResponseResult<LoginResponseModel>>  login(@RequestBody LoginRequest loginRequest) {
        return Mono.fromCallable(() -> {
            // 查询用户
            UsersEntity user = usersService.getOne(
                    new QueryWrapper<UsersEntity>().eq("username", loginRequest.getUsername())
            );

            if (user == null) {
                return ResponseResult.failed(401, "用户不存在");
            }

            // 验证密码
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseResult.failed(401, "用户名或密码错误");
            }

            // 生成 JWT token
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            claims.put("username", user.getUsername());

            String token = jwtUtil.generateToken(user.getUsername(), claims);

            LoginResponseModel result = new LoginResponseModel();
            result.setToken(token);
            result.setUsername(user.getUsername());
            result.setAvatar(user.getAvatar());
            result.setNickname(user.getUsername());
            result.setExpire(jwtUtil.getExpirationDate(token));

            return ResponseResult.success("登录成功", result);
        });
    }

    /**
     * 登录请求对象
     */

}
