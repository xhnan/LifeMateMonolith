package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerBudget;
import com.xhn.ledger.mapper.LedgerBudgetMapper;
import com.xhn.ledger.service.LedgerBudgetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预算表，记录用户为不同分类设置的预算 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerBudgetServiceImpl extends ServiceImpl<LedgerBudgetMapper, LedgerBudget> implements LedgerBudgetService {

}
