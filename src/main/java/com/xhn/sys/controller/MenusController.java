package com.xhn.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xhn.base.ResponseResult;
import com.xhn.exception.ApplicationException;
import com.xhn.sys.model.MenusEntity;
import com.xhn.sys.model.MenusModel;
import com.xhn.sys.model.RouterModel;
import com.xhn.sys.service.IMenusService;
import com.xhn.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
@RestController
@RequestMapping("/sys/menus")
public class MenusController {

    @Autowired
    IMenusService menusService;


//    获取菜单列表
//    public


    //添加菜单
    @Operation(summary = "添加菜单")
    @PostMapping("/addMenus")
    public ResponseResult<String> addMenus(@RequestBody MenusEntity menusEntity) {
        menusService.save(menusEntity);
        return ResponseResult.success("成功");
    }

    //修改
    @Operation(summary = "修改")
    @PostMapping("/updateMenus")
    public ResponseResult<String> updateMenus(@RequestBody MenusEntity menusEntity) {
        menusService.updateById(menusEntity);
        return ResponseResult.success("成功");
    }


    //删除
    @Operation(summary = "删除")
    @DeleteMapping("/deleteMenus")
    public ResponseResult<String> deleteMenus(@RequestParam("id") Long id) {
        //检查是否有子菜单，有则不能删除
        QueryWrapper<MenusEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        long count = menusService.count(queryWrapper);
        if (count > 0) {
            throw new ApplicationException("有子菜单，不能删除");
        }

        menusService.removeById(id);
        return ResponseResult.success("成功");
    }





    /**
     * 获取所有菜单列表
     * @return
     */
    @Operation(summary = "获取所有菜单列表")
    @GetMapping("/getAllMenus")
    public ResponseResult<List<MenusModel>> getAllMenus(@RequestParam(required = false) String name){
        List<MenusModel> menus = menusService.getAllMenus( name);

        return ResponseResult.success(menus);
    }

    @Operation(summary = "获取用户菜单列表")
    @GetMapping("/getUserMenus")
    public ResponseResult<List<RouterModel>> getUserMenus(){
        //从上下文中获取用户信息，然后获取用户菜单
        Long currentUserId = SecurityUtils.getCurrentUserId();

        List<RouterModel> menus = menusService.getUserMenus(currentUserId);

        return ResponseResult.success(menus);
    }


}
