package com.xhn.sys.service.impl;

import com.xhn.sys.model.UserRolesEntity;
import com.xhn.sys.mapper.UserRolesMapper;
import com.xhn.sys.service.IUserRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关联表 服务实现类
 * </p>
 *
 * @author xhn
 * @since 2025-11-13
 */
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRolesEntity> implements IUserRolesService {

}
