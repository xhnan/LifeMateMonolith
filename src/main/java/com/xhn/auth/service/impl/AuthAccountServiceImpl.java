package com.xhn.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhn.auth.model.AuthAccount;
import com.xhn.auth.mapper.AuthAccountMapper;
import com.xhn.auth.model.AuthAccountDTO;
import com.xhn.auth.service.AuthAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhn.exception.LifeMateException;
import com.xhn.utils.JwtUtil;
import com.xhn.utils.UserIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-07
 */
@Service
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

    @Autowired
    JwtUtil  jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthAccountServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(AuthAccountDTO authAccountDTO) {
        String username = authAccountDTO.getUsername();
        String password = authAccountDTO.getPasswordHash();
        //
        long count = count(new QueryWrapper<>(AuthAccount.class)
                .eq("username", username)
        );
        if (count > 0) {
            throw new LifeMateException("用户名已存在");
        }


        Long userId= UserIdUtil.nextId();
        AuthAccount authAccount = new AuthAccount();
        authAccount.setUserId(userId);
        authAccount.setUsername(username);
        //密码处理
        // 密码处理 - 使用BCrypt加密
        String encodedPassword = passwordEncoder.encode(password);
        authAccount.setPasswordHash(encodedPassword);
        save(authAccount);

    }

    @Override
    public String login(AuthAccountDTO authAccountDTO) {
        String username = authAccountDTO.getUsername();
        String password = authAccountDTO.getPassword();
        // 根据用户名查询用户
        AuthAccount authAccount = getOne(new QueryWrapper<AuthAccount>()
                .eq("username", username)
        );
        if (authAccount == null) {
            throw new LifeMateException("账号不存在");
        }
        // 验证密码
        if (!passwordEncoder.matches(password, authAccount.getPasswordHash())) {
            throw new LifeMateException("密码错误");
        }
        //登录成功，返回token
        String s = jwtUtil.generateToken(String.valueOf(authAccount.getUserId()));


        return s;
    }
}
