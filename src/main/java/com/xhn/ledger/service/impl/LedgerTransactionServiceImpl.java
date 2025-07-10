package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerTransaction;
import com.xhn.ledger.mapper.LedgerTransactionMapper;
import com.xhn.ledger.service.LedgerTransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交易表，记录用户的每一笔交易 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerTransactionServiceImpl extends ServiceImpl<LedgerTransactionMapper, LedgerTransaction> implements LedgerTransactionService {

}
