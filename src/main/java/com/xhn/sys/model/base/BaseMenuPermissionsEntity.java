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
 * 菜单-权限关联表
 * </p>
 *
 * @author xhn
 * @since 2025-11-26
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("sys_menu_permissions")
public class BaseMenuPermissionsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 权限ID
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
