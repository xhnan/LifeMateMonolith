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
 * 用户-角色关联表
 * </p>
 *
 * @author xhn
 * @since 2025-11-14
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("sys_user_roles")
public class BaseUserRolesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
}
