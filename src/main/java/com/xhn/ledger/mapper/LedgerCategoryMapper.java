package com.xhn.ledger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhn.ledger.model.LedgerCategory;
import com.xhn.ledger.model.LedgerCategoryDTO;

import java.util.List;

/**
 * <p>
 * 分类表，记录所有交易的分类信息（支持一级分类和二级分类） Mapper 接口
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
public interface LedgerCategoryMapper extends BaseMapper<LedgerCategory> {

    List<LedgerCategoryDTO> getCategoryList(Long userId);
}
