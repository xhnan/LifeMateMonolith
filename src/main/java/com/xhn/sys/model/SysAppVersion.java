package com.xhn.sys.model;

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
 * app更新日志表
 * </p>
 *
 * @author xhn
 * @since 2025-07-08
 */
@Getter
@Setter
@ToString
@TableName("sys_app_version")
public class SysAppVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("platform")
    private String platform;

    @TableField("version")
    private String version;

    @TableField("build_number")
    private Integer buildNumber;

    @TableField("is_force_update")
    private Boolean isForceUpdate;

    @TableField("download_url")
    private String downloadUrl;

    @TableField("changelog")
    private String changelog;

    @TableField("release_date")
    private LocalDateTime releaseDate;

    @TableField("created_time")
    private LocalDateTime createdTime;

    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
