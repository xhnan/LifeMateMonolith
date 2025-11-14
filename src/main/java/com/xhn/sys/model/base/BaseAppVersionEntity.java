package com.xhn.sys.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * app更新日志表
 * </p>
 *
 * @author xhn
 * @since 2025-11-14
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("sys_app_version")
public class BaseAppVersionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String platform;

    private String version;

    private Integer buildNumber;

    private Boolean isForceUpdate;

    private String downloadUrl;

    private String changelog;

    private LocalDateTime releaseDate;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
