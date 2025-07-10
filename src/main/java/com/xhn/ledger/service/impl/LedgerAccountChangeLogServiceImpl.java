package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerAccountChangeLog;
import com.xhn.ledger.mapper.LedgerAccountChangeLogMapper;
import com.xhn.ledger.service.LedgerAccountChangeLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户变动日志表，记录账户余额变动的详细信息 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerAccountChangeLogServiceImpl extends ServiceImpl<LedgerAccountChangeLogMapper, LedgerAccountChangeLog> implements LedgerAccountChangeLogService {

}
