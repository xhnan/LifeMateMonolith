package com.xhn.sys.service;

import com.xhn.sys.model.MenusEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
public interface IMenusService extends IService<MenusEntity> {


    public void initMenu();

}
