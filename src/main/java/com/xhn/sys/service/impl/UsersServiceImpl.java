package com.xhn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xhn.sys.model.UsersEntity;
import com.xhn.sys.mapper.UsersMapper;
import com.xhn.sys.service.IMenusService;
import com.xhn.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements IUsersService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    IMenusService menusService;

    /**
     * 初始化用户
     * @return
     */
    @Override
    @Transactional
    public boolean createAdminIfNotExists() {
        String username = "admin";
        UsersEntity existing = this.getOne(new QueryWrapper<UsersEntity>().eq("username", username));
        if (existing != null) {
            return false;
        }
        String password = "admin123"; //管理员默认admin
        //加密--使用security的加密
        String encode = passwordEncoder.encode(password);

        UsersEntity user = new UsersEntity();
        user.setUsername(username)
                .setPassword(encode)
                .setStatus(true)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());
//        menusService.initMenu();
        return this.save(user);
    }

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
    private void initMenu(){

    }



}
