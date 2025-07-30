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
 * 用户授权设备信息表
 * </p>
 *
 * @author xhn
 * @since 2025-07-24
 */
@Getter
@Setter
@ToString
@TableName("pm_trusted_device")
public class PmTrustedDevice implements Serializable {

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
     * 设备唯一标识
     */
    @TableField("device_id")
    private String deviceId;

    /**
     * 设备名称（如 iPhone 15）
     */
    @TableField("device_name")
    private String deviceName;

    /**
     * 该设备是否开启生物认证
     */
    @TableField("biometric_enabled")
    private Boolean biometricEnabled;

    /**
     * 最近一次授权时间
     */
    @TableField("last_auth_time")
    private LocalDateTime lastAuthTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
}
