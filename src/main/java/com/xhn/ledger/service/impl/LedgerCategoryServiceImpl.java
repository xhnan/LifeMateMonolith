package com.xhn.ledger.service.impl;

import com.xhn.ledger.model.LedgerCategory;
import com.xhn.ledger.mapper.LedgerCategoryMapper;
import com.xhn.ledger.model.LedgerCategoryDTO;
import com.xhn.ledger.service.LedgerCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表，记录所有交易的分类信息（支持一级分类和二级分类） 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Service
public class LedgerCategoryServiceImpl extends ServiceImpl<LedgerCategoryMapper, LedgerCategory> implements LedgerCategoryService {

    @Override
    public List<LedgerCategoryDTO> getCategoryList(Long aLong) {
        return baseMapper.getCategoryList(aLong);
    }
}
