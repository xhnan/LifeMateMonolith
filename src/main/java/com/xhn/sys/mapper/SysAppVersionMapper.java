package com.xhn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhn.sys.model.SysAppVersion;
import com.xhn.sys.model.SysAppVersionDTO;

/**
 * <p>
 * app更新日志表 Mapper 接口
 * </p>
 *
 * @author xhn
 * @since 2025-07-08
 */
public interface SysAppVersionMapper extends BaseMapper<SysAppVersion> {

    SysAppVersionDTO getLastVersion();
}
