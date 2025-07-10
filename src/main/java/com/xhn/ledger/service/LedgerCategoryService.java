package com.xhn.ledger.service;

import com.xhn.ledger.model.LedgerCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhn.ledger.model.LedgerCategoryDTO;

import java.util.List;

/**
 * <p>
 * 分类表，记录所有交易的分类信息（支持一级分类和二级分类） 服务类
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
public interface LedgerCategoryService extends IService<LedgerCategory> {

    List<LedgerCategoryDTO> getCategoryList(Long aLong);
}
