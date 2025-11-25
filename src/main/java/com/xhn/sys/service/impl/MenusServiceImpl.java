package com.xhn.sys.service.impl;

import com.xhn.sys.model.MenusEntity;
import com.xhn.sys.mapper.MenusMapper;
import com.xhn.sys.model.MenusModel;
import com.xhn.sys.service.IMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
@Service
public class MenusServiceImpl extends ServiceImpl<MenusMapper, MenusEntity> implements IMenusService {

    /**
     * const permissionRouter = {
     *   path: "/permission",
     *   meta: {
     *     title: "权限管理",
     *     icon: "ep:lollipop",
     *     rank: 10
     *   },
     *   children: [
     *     {
     *       path: "/permission/page/index",
     *       name: "PermissionPage",
     *       meta: {
     *         title: "页面权限",
     *         roles: ["admin", "common"]
     *       }
     *     },
     *     {
     *       path: "/permission/button",
     *       meta: {
     *         title: "按钮权限",
     *         roles: ["admin", "common"]
     *       },
     *       children: [
     *         {
     *           path: "/permission/button/router",
     *           component: "permission/button/index",
     *           name: "PermissionButtonRouter",
     *           meta: {
     *             title: "路由返回按钮权限",
     *             auths: [
     *               "permission:btn:add",
     *               "permission:btn:edit",
     *               "permission:btn:delete"
     *             ]
     *           }
     *         },
     *         {
     *           path: "/permission/button/login",
     *           component: "permission/button/perms",
     *           name: "PermissionButtonLogin",
     *           meta: {
     *             title: "登录接口返回按钮权限"
     *           }
     *         }
     *       ]
     *     }
     *   ]
     * };
     */
    @Override
    public void initMenu() {
        MenusEntity root = new MenusEntity();
        root.setRoutePath("/permission");
        root.setTitle("权限管理");
        root.setIcon("ep:lollipop");
        root.setRank(10);
        this.save(root);
        MenusEntity pagePermission = new MenusEntity();
        pagePermission.setParentId(root.getId());
        pagePermission.setRoutePath("/permission/page/index");
        pagePermission.setRouteName("PermissionPage");
        pagePermission.setTitle("页面权限");
        this.save(pagePermission);

        MenusEntity buttonPermission = new MenusEntity();
        buttonPermission.setParentId(root.getId());
        buttonPermission.setRoutePath("/permission/button");
        buttonPermission.setTitle("按钮权限");
        // buttonPermission.setRoles("admin,common"); // 如果有角色字段可设置
        this.save(buttonPermission);
        MenusEntity routerButton = new MenusEntity();
        routerButton.setParentId(buttonPermission.getId());
        routerButton.setRoutePath("/permission/button/router");
        routerButton.setRouteName("PermissionButtonRouter");
        routerButton.setTitle("路由返回按钮权限");
        // 若有 component 字段：
        // routerButton.setComponent("permission/button/index");
        // 若有 auths 字段，可存成逗号分隔字符串或 JSON：
        // routerButton.setAuths("permission:btn:add,permission:btn:edit,permission:btn:delete");
        this.save(routerButton);
        // /permission/button/login
        MenusEntity loginButton = new MenusEntity();
        loginButton.setParentId(buttonPermission.getId());
        loginButton.setRoutePath("/permission/button/login");
        loginButton.setRouteName("PermissionButtonLogin");
        loginButton.setTitle("登录接口返回按钮权限");
        // loginButton.setComponent("permission/button/perms");
        this.save(loginButton);

    }

    @Override
    public List<MenusModel> getUserMenus(Long userId) {
        //

//        baseMapper.getMenusByUserId(userId);
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

        return List.of();
    }

    @Override
    public List<MenusModel> getAllMenus() {
        //维护页面的菜单管理 principalInfo
        List<MenusEntity> menusEntities = baseMapper.selectList(null);
        // 组合诚为树形结构返回给前端 MenusModel
        List<MenusModel> menusModels = new ArrayList<>();



        return List.of();
    }
}
