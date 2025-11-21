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
 * 系统菜单表
 * </p>
 *
 * @author xhn
 * @since 2025-11-21
 */
@Getter
@Setter
@ToString
@TableName("sys_menus")
@Accessors(chain = true)
public class BaseMenusEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String routeName;

    /**
     * 父菜单ID，NULL表示顶级菜单
     */
    private Long parentId;

    /**
     * 菜单路径或链接
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单排序号
     */
    private Integer rank;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    private String title;

    /**
     * 路由路径
     */
    private String routePath;

    /**
     * 路由组件
     */
    private String component;
}
