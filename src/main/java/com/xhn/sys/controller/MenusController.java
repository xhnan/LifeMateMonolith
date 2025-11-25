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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @PostMapping("/getUserMenus")
    public ResponseResult<JSONPObject> getUserMenus(){
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

        return ResponseResult.success(
                new JSONPObject("menus", """
                        {
                          "path": "/permission",
                          "meta": {
                            "title": "权限管理",
                            "icon": "ep:lollipop",
                            "rank": 10
                          },
                          "children": [
                            {
                              "path": "/permission/page/index",
                              "name": "PermissionPage",
                              "meta": {
                                "title": "页面权限",
                                "roles": ["admin", "common"]
                              }
                            },
                            {
                              "path": "/permission/button",
                              "meta": {
                                "title": "按钮权限",
                                "roles": ["admin", "common"]
                              },
                              "children": [
                                {
                                  "path": "/permission/button/router",
                                  "component": "permission/button/index",
                                  "name": "PermissionButtonRouter",
                                  "meta": {
                                    "title": "路由返回按钮权限",
                                    "auths": [
                                      "permission:btn:add",
                                      "permission:btn:edit",
                                      "permission:btn:delete"
                                    ]
                                  }
                                },
                                {
                                  "path": "/permission/button/login",
                                  "component": "permission/button/perms",
                                  "name": "PermissionButtonLogin",
                                  "meta": {
                                    "title": "登录接口返回按钮权限"
                                  }
                                }
                              ]
                            }
                          ]
                        }
                        """)
        );




//        //从上下文中获取用户信息，然后获取用户菜单
//        Long currentUserId = SecurityUtils.getCurrentUserId();
//
//        List<MenusModel> menus = menusService.getUserMenus(currentUserId);
//
//        return ResponseResult.success(menus);
    }


}
