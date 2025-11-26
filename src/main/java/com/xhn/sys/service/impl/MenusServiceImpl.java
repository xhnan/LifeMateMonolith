package com.xhn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xhn.sys.enums.MenuTypeEnum;
import com.xhn.sys.model.MenusEntity;
import com.xhn.sys.mapper.MenusMapper;
import com.xhn.sys.model.MenusModel;
import com.xhn.sys.model.RouterMeta;
import com.xhn.sys.model.RouterModel;
import com.xhn.sys.service.IMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
     * path: "/permission",
     * meta: {
     * title: "权限管理",
     * icon: "ep:lollipop",
     * rank: 10
     * },
     * children: [
     * {
     * path: "/permission/page/index",
     * name: "PermissionPage",
     * meta: {
     * title: "页面权限",
     * roles: ["admin", "common"]
     * }
     * },
     * {
     * path: "/permission/button",
     * meta: {
     * title: "按钮权限",
     * roles: ["admin", "common"]
     * },
     * children: [
     * {
     * path: "/permission/button/router",
     * component: "permission/button/index",
     * name: "PermissionButtonRouter",
     * meta: {
     * title: "路由返回按钮权限",
     * auths: [
     * "permission:btn:add",
     * "permission:btn:edit",
     * "permission:btn:delete"
     * ]
     * }
     * },
     * {
     * path: "/permission/button/login",
     * component: "permission/button/perms",
     * name: "PermissionButtonLogin",
     * meta: {
     * title: "登录接口返回按钮权限"
     * }
     * }
     * ]
     * }
     * ]
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
    public List<RouterModel> getUserMenus(Long userId) {
        List<MenusEntity>  menusEntities = baseMapper.selectList(null);
        List<RouterModel> routerModels = new ArrayList<>();
        List<MenusEntity> list = menusEntities.stream().filter(menu -> menu.getParentId() == null).toList();
        for (MenusEntity menusEntity : list) {
            RouterModel router = getUserMenuChildren(menusEntity, menusEntities);
            routerModels.add(router);
        }

        return routerModels;
    }


    public RouterModel getUserMenuChildren(MenusEntity parent, List<MenusEntity> allMenus) {
        RouterModel router = convertToRouter(parent);
        router.setChildren(new ArrayList<>());

        // 如果 parent.id 为 null，无法匹配子节点，直接返回
        if (parent.getId() == null) {
            return router;
        }

        List<MenusEntity> childEntities = allMenus.stream()
                .filter(menu -> parent.getId().equals(menu.getParentId()))
                .toList();

        if (!childEntities.isEmpty()) {
            List<RouterModel> childRouters = new ArrayList<>();
            for (MenusEntity childEntity : childEntities) {
                RouterModel childRouter = getUserMenuChildren(childEntity, allMenus);
                childRouters.add(childRouter);
            }
            router.setChildren(childRouters);
        }

        return router;
    }


    private RouterModel convertToRouter(MenusEntity entity) {
        RouterModel router = new RouterModel();

        // 只能映射 RouterModel 中实际存在的字段
        router.setId(entity.getId());
        router.setPath(entity.getRoutePath());
        router.setName(entity.getRouteName());
        router.setComponent(entity.getComponent());

        // 使用 RouterMeta 填充标题、图标、排序等信息
        RouterMeta meta = new RouterMeta();
        meta.setTitle(entity.getTitle());
        meta.setIcon(entity.getIcon());
        meta.setRank(entity.getRank());
        // 若实体中存在角色/权限字段，可在此解析并设置到 meta 的 roles/auths
        router.setMeta(meta);

        // RouterModel 的 children 是单一 RouterModel（当前模型），不初始化为 List，保留为 null
        router.setChildren(null);

        return router;
    }

    @Override
    public List<MenusModel> getAllMenus(String name) {
        // 查询所有菜单
        List<MenusEntity> menusEntities;
        if (name != null && !name.isEmpty()) {
            menusEntities = baseMapper.selectList(
                    new LambdaQueryWrapper<MenusEntity>()
                            .like(MenusEntity::getTitle, name)
            );
        } else {
            menusEntities = baseMapper.selectList(null);
        }

        // 转换为 MenusModel
        List<MenusModel> allMenus = menusEntities.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());

        // 构建树形结构
        return buildMenuTree(allMenus);
    }


    private List<MenusModel> buildMenuTree(List<MenusModel> allMenus) {
        // 找出所有根节点
        List<MenusModel> rootMenus = allMenus.stream()
                .filter(menu -> menu.getParentId() == null || menu.getParentId() == 0)
                .toList();

        // 递归设置子节点
        rootMenus.forEach(root -> setChildren(root, allMenus));

        // 按排序字段排序
        return rootMenus.stream()
                .sorted(Comparator.comparing(MenusModel::getSort, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    private MenusModel convertToModel(MenusEntity entity) {
        MenusModel model = new MenusModel();
        model.setId(entity.getId());
        model.setParentId(entity.getParentId());
        model.setName(entity.getTitle());
        model.setRouteName(entity.getRouteName());
        model.setPath(entity.getRoutePath());
        model.setIcon(entity.getIcon());
        model.setSort(entity.getRank());
        model.setComponent(entity.getComponent());

        // 如果 MenusEntity 有这些字段,取消注释
        // model.setPerm(entity.getPerm());
        // model.setType(entity.getType());
        // model.setHidden(entity.getHidden());
        // model.setKeepAlive(entity.getKeepAlive());

        // 暂时没有的字段设置为默认值
        model.setPerm(null);
        model.setType(MenuTypeEnum.fromValueOrThrow(entity.getMenuType()));
        model.setRightIcon(null);
        model.setEnterTransition(null);
        model.setLeaveTransition(null);
        model.setActiveMenu(null);
        model.setRedirect(null);

        // Boolean 类型字段设置默认值
        model.setHidden(false);
        model.setHideParent(false);
        model.setKeepAlive(false);
        model.setAffix(false);
        model.setClosable(true);

        return model;
    }



    /**
     * 递归设置子节点
     */
    private void setChildren(MenusModel parent, List<MenusModel> allMenus) {
        List<MenusModel> children = allMenus.stream()
                .filter(menu -> parent.getId().equals(menu.getParentId()))
                .sorted(Comparator.comparing(MenusModel::getSort, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        if (!children.isEmpty()) {
            parent.setChildren(children);
            // 递归设置子节点的子节点
            children.forEach(child -> setChildren(child, allMenus));
        }

    }

}
