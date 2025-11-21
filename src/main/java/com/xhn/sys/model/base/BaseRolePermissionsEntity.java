package com.xhn.sys.model.base;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色-权限关联表
 * </p>
 *
 * @author xhn
 * @since 2025-11-21
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("sys_role_permissions")
public class BaseRolePermissionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
