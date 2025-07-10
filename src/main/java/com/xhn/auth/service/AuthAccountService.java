package com.xhn.auth.service;

import com.xhn.auth.model.AuthAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhn.auth.model.AuthAccountDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhn
 * @since 2025-07-07
 */
public interface AuthAccountService extends IService<AuthAccount> {

    void register(AuthAccountDTO authAccountDTO);

    String login(AuthAccountDTO authAccountDTO);
}
