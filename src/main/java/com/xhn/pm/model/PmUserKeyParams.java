package com.xhn.pm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户主密码派生参数表
 * </p>
 *
 * @author xhn
 * @since 2025-07-24
 */
@Getter
@Setter
@ToString
@TableName("pm_user_key_params")
public class PmUserKeyParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * Argon2 派生用盐，Base64 编码
     */
    @TableField("salt")
    private String salt;

    /**
     * Argon2 参数配置（timeCost, memoryCost 等）
     */
    @TableField("argon2_params")
    private Object argon2Params;

    /**
     * 是否启用生物认证解锁
     */
    @TableField("enable_biometrics")
    private Boolean enableBiometrics;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
