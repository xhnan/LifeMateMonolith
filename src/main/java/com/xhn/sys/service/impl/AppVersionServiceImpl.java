package com.xhn.sys.service.impl;

import com.xhn.sys.model.AppVersionEntity;
import com.xhn.sys.mapper.AppVersionMapper;
import com.xhn.sys.service.IAppVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * app更新日志表 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersionEntity> implements IAppVersionService {

}
