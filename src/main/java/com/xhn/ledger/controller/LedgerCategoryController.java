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
    public ResponseResult<String> add(@RequestBody LedgerCategoryDTO ledgerCategoryDTO,@CurrentSecurityContext SecurityContext securityContext) {
        if (ledgerCategoryDTO.getCategoryName() == null || ledgerCategoryDTO.getCategoryName().isEmpty()) {
            return ResponseResult.error("分类名称不能为空");
        }
        ledgerCategoryDTO.setId(null); // 确保添加时ID为null
        String userId = securityContext.getAuthentication().getPrincipal().toString();
        ledgerCategoryDTO.setUserId(Long.valueOf(userId));
        boolean save = ledgerCategoryService.save(ledgerCategoryDTO);
        if (save) {
            return ResponseResult.success("添加成功");
        } else {
            return ResponseResult.error("添加失败");
        }

    }

    @PostMapping("/update")
    public ResponseResult<String> update(@RequestBody LedgerCategoryDTO ledgerCategoryDTO, @CurrentSecurityContext SecurityContext securityContext) {
        if (ledgerCategoryDTO.getId() == null) {
            return ResponseResult.error("分类ID不能为空");
        }
        if (ledgerCategoryDTO.getCategoryName() == null || ledgerCategoryDTO.getCategoryName().isEmpty()) {
            return ResponseResult.error("分类名称不能为空");
        }

        String userId = securityContext.getAuthentication().getPrincipal().toString();
        ledgerCategoryDTO.setUserId(Long.valueOf(userId));
        boolean update = ledgerCategoryService.updateById(ledgerCategoryDTO);
        if (update) {
            return ResponseResult.success("更新成功");
        } else {
            return ResponseResult.error("更新失败");
        }
    }
    @PostMapping("/delete")
    public ResponseResult<String> delete(@RequestBody LedgerCategoryDTO ledgerCategoryDTO) {
        if (ledgerCategoryDTO.getId() == null) {
            return ResponseResult.error("分类ID不能为空");
        }

        boolean remove = ledgerCategoryService.removeById(ledgerCategoryDTO.getId());
        if (remove) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.error("删除失败");
        }
    }
}
