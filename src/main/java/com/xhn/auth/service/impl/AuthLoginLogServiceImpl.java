package com.xhn.auth.service.impl;

import com.xhn.auth.model.AuthLoginLog;
import com.xhn.auth.mapper.AuthLoginLogMapper;
import com.xhn.auth.service.AuthLoginLogService;
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
public class AuthLoginLogServiceImpl extends ServiceImpl<AuthLoginLogMapper, AuthLoginLog> implements AuthLoginLogService {

}
