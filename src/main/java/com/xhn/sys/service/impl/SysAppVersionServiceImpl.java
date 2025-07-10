package com.xhn.sys.service.impl;

import com.xhn.sys.model.SysAppVersion;
import com.xhn.sys.mapper.SysAppVersionMapper;
import com.xhn.sys.model.SysAppVersionDTO;
import com.xhn.sys.service.SysAppVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * app更新日志表 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-07-08
 */
@Service
public class SysAppVersionServiceImpl extends ServiceImpl<SysAppVersionMapper, SysAppVersion> implements SysAppVersionService {

    @Override
    public SysAppVersionDTO checkVersion() {


        return baseMapper.getLastVersion();
    }
}
