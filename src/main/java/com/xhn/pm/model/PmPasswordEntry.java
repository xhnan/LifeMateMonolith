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
 * 用户密码条目表
 * </p>
 *
 * @author xhn
 * @since 2025-07-24
 */
@Getter
@Setter
@ToString
@TableName("pm_password_entry")
public class PmPasswordEntry implements Serializable {

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
     * 密码条目标题（如“邮箱账号”）
     */
    @TableField("title")
    private String title;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 用 masterKey 加密后的密码密文（Base64）
     */
    @TableField("encrypted_pwd")
    private String encryptedPwd;

    /**
     * AES-GCM IV，Base64 编码
     */
    @TableField("iv")
    private String iv;

    /**
     * AES-GCM 认证标签，Base64 编码
     */
    @TableField("tag")
    private String tag;

    /**
     * 密码条目关联的网址或连接地址
     */
    @TableField("url")
    private String url;

    /**
     * 备注信息
     */
    @TableField("note")
    private String note;

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
