package com.xhn.auth.service.impl;

import com.xhn.auth.model.AuthToken;
import com.xhn.auth.mapper.AuthTokenMapper;
import com.xhn.auth.service.AuthTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AuthTokenServiceImpl extends ServiceImpl<AuthTokenMapper, AuthToken> implements AuthTokenService {

}
