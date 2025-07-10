package com.xhn.sys.service;

import com.xhn.sys.model.SysAppVersion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhn.sys.model.SysAppVersionDTO;

/**
 * <p>
 * app更新日志表 服务类
 * </p>
 *
 * @author xhn
 * @since 2025-07-08
 */
public interface SysAppVersionService extends IService<SysAppVersion> {

    SysAppVersionDTO checkVersion();
}
