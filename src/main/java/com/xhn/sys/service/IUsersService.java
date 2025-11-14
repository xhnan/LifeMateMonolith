package com.xhn.sys.service;

import com.xhn.sys.model.UsersEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
public interface IUsersService extends IService<UsersEntity> {

    boolean createAdminIfNotExists();
}
