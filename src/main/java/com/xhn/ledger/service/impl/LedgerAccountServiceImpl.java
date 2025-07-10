package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerAccount;
import com.xhn.ledger.mapper.LedgerAccountMapper;
import com.xhn.ledger.service.LedgerAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表，记录每个用户的账户信息 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerAccountServiceImpl extends ServiceImpl<LedgerAccountMapper, LedgerAccount> implements LedgerAccountService {

}
