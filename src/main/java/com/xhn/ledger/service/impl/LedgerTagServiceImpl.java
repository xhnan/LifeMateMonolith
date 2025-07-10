package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerTag;
import com.xhn.ledger.mapper.LedgerTagMapper;
import com.xhn.ledger.service.LedgerTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表，记录用户在交易中添加的标签 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerTagServiceImpl extends ServiceImpl<LedgerTagMapper, LedgerTag> implements LedgerTagService {

}
