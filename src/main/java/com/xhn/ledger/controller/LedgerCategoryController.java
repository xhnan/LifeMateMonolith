package com.xhn.ledger.controller;

import com.xhn.base.ResponseResult;
import com.xhn.ledger.model.LedgerCategoryDTO;
import com.xhn.ledger.service.LedgerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * <p>
 * 分类表，记录所有交易的分类信息（支持一级分类和二级分类） 前端控制器
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@RestController
@RequestMapping("/ledger/category")
public class LedgerCategoryController {

    @Autowired
    LedgerCategoryService ledgerCategoryService;

    Mono<String> getUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(authentication -> {
                    // 从Authentication对象中获取用户ID
                    return authentication.getPrincipal().toString();
                });
    }


    @GetMapping("/list")
    public ResponseResult<List<LedgerCategoryDTO>> getCategoryList(@CurrentSecurityContext SecurityContext securityContext) {
        String userId = securityContext.getAuthentication().getPrincipal().toString();
        List<LedgerCategoryDTO> categoryList = ledgerCategoryService.getCategoryList(Long.valueOf(userId));
        return ResponseResult.success(categoryList);

    }

    @PostMapping("/add")
    public ResponseResult<String> add(@RequestBody LedgerCategoryDTO ledgerCategoryDTO) {
        boolean save = ledgerCategoryService.save(ledgerCategoryDTO);
        if (save) {
            return ResponseResult.success("添加成功");
        } else {
            return ResponseResult.error("添加失败");
        }

    }

}
