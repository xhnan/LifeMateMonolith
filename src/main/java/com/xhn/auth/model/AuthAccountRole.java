package com.xhn.auth.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhn
 * @since 2025-07-07
 */
@Getter
@Setter
@ToString
@TableName("auth_account_role")
public class AuthAccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("account_id")
    private Long accountId;

    @TableField("role_id")
    private Long roleId;

    @TableId("id")
    private Long id;
}
