package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerNotification;
import com.xhn.ledger.mapper.LedgerNotificationMapper;
import com.xhn.ledger.service.LedgerNotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知表，记录与记账相关的提醒和通知 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerNotificationServiceImpl extends ServiceImpl<LedgerNotificationMapper, LedgerNotification> implements LedgerNotificationService {

}
