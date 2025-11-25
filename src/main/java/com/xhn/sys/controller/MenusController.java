package com.xhn.sys.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xhn.base.ResponseResult;
import com.xhn.sys.model.MenusModel;
import com.xhn.sys.service.IMenusService;
import com.xhn.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取所有菜单列表
     * @return
     */
    @Operation(summary = "获取所有菜单列表")
    @PostMapping("/getAllMenus")
    public ResponseResult<List<MenusModel>> getMenus(){
        List<MenusModel> menus = menusService.getAllMenus();

        return ResponseResult.success(menus);
    }

    @Operation(summary = "获取用户菜单列表")
    @GetMapping("/getUserMenus")
    public ResponseResult<List<Map<String,Object>>> getUserMenus(){
        //{
        //     *   path: "/permission",
        //     *   meta: {
        //     *     title: "权限管理",
        //     *     icon: "ep:lollipop",
        //     *     rank: 10
        //     *   },
        //     *   children: [
        //     *     {
        //     *       path: "/permission/page/index",
        //     *       name: "PermissionPage",
        //     *       meta: {
        //     *         title: "页面权限",
        //     *         roles: ["admin", "common"]
        //     *       }
        //     *     },
        //     *     {
        //     *       path: "/permission/button",
        //     *       meta: {
        //     *         title: "按钮权限",
        //     *         roles: ["admin", "common"]
        //     *       },
        //     *       children: [
        //     *         {
        //     *           path: "/permission/button/router",
        //     *           component: "permission/button/index",
        //     *           name: "PermissionButtonRouter",
        //     *           meta: {
        //     *             title: "路由返回按钮权限",
        //     *             auths: [
        //     *               "permission:btn:add",
        //     *               "permission:btn:edit",
        //     *               "permission:btn:delete"
        //     *             ]
        //     *           }
        //     *         },
        //     *         {
        //     *           path: "/permission/button/login",
        //     *           component: "permission/button/perms",
        //     *           name: "PermissionButtonLogin",
        //     *           meta: {
        //     *             title: "登录接口返回按钮权限"
        //     *           }
        //     *         }
        //     *       ]
        //     *     }
        //     *   ]
        //     * }
        // 直接返回这个json
        Map<String,Object> map = new HashMap<>();
        map.put("path", "/permission");

        Map<String,Object> meta = new HashMap<>();
        meta.put("title", "权限管理");
        meta.put("icon", "ep:lollipop");
        meta.put("rank", 10);
        map.put("meta", meta);

        List<Map<String,Object>> children = new ArrayList<>();

// 第一个子菜单: 页面权限
        Map<String,Object> child1 = new HashMap<>();
        child1.put("path", "/permission/page/index");
        child1.put("name", "PermissionPage");
        Map<String,Object> child1Meta = new HashMap<>();
        child1Meta.put("title", "页面权限");
        child1Meta.put("roles", List.of("admin", "common"));
        child1.put("meta", child1Meta);
        children.add(child1);

// 第二个子菜单: 按钮权限
        Map<String,Object> child2 = new HashMap<>();
        child2.put("path", "/permission/button");
        Map<String,Object> child2Meta = new HashMap<>();
        child2Meta.put("title", "按钮权限");
        child2Meta.put("roles", List.of("admin", "common"));
        child2.put("meta", child2Meta);

// 按钮权限的子菜单
        List<Map<String,Object>> child2Children = new ArrayList<>();

// 路由返回按钮权限
        Map<String,Object> child2_1 = new HashMap<>();
        child2_1.put("path", "/permission/button/router");
        child2_1.put("component", "permission/button/index");
        child2_1.put("name", "PermissionButtonRouter");
        Map<String,Object> child2_1Meta = new HashMap<>();
        child2_1Meta.put("title", "路由返回按钮权限");
        child2_1Meta.put("auths", List.of("permission:btn:add", "permission:btn:edit", "permission:btn:delete"));
        child2_1.put("meta", child2_1Meta);
        child2Children.add(child2_1);

// 登录接口返回按钮权限
        Map<String,Object> child2_2 = new HashMap<>();
        child2_2.put("path", "/permission/button/login");
        child2_2.put("component", "permission/button/perms");
        child2_2.put("name", "PermissionButtonLogin");
        Map<String,Object> child2_2Meta = new HashMap<>();
        child2_2Meta.put("title", "登录接口返回按钮权限");
        child2_2.put("meta", child2_2Meta);
        child2Children.add(child2_2);

        child2.put("children", child2Children);
        children.add(child2);

        map.put("children", children);
        List<Map<String,Object>> menus = new ArrayList<>();
        menus.add(map);
        return ResponseResult.success(menus);




//        //从上下文中获取用户信息，然后获取用户菜单
//        Long currentUserId = SecurityUtils.getCurrentUserId();
//
//        List<MenusModel> menus = menusService.getUserMenus(currentUserId);
//
//        return ResponseResult.success(menus);
    }


}
