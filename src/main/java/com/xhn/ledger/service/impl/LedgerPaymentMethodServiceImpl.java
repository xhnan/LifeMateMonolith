package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerPaymentMethod;
import com.xhn.ledger.mapper.LedgerPaymentMethodMapper;
import com.xhn.ledger.service.LedgerPaymentMethodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付方式表，记录用户的支付方式（如现金、银行卡等） 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerPaymentMethodServiceImpl extends ServiceImpl<LedgerPaymentMethodMapper, LedgerPaymentMethod> implements LedgerPaymentMethodService {

}
