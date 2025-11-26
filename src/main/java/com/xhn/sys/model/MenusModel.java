package com.xhn.sys.model;

import com.xhn.sys.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author xhn
 * @date 2025/11/24 17:10
 * @description
 */
@Data
@Schema(description = "菜单")
public class MenusModel {

    @Schema(description = "菜单ID")
    @NotNull(message = "菜单ID不能为空")
    private Long id;

    @Schema(description = "父菜单ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    @Schema(description = "路由名称")
    private String routeName;

    @Schema(description = "菜单类型")
    @NotNull(message = "菜单类型不能为空")
    private MenuTypeEnum type;

    @Schema(description = "路由路径或iframe/外链地址")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @Schema(description = "路由重定向")
    private String redirect;

    @Schema(description = "权限标识(按钮)")
    private String perm;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "右侧图标")
    private String rightIcon;

    @Schema(description = "进场动画")
    private String enterTransition;

    @Schema(description = "离场动画")
    private String leaveTransition;

    @Schema(description = "菜单激活(高亮其他)")
    private String activeMenu;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "是否隐藏自身")
    private Boolean hidden;

    @Schema(description = "隐藏父级菜单")
    private Boolean hideParent;

    @Schema(description = "缓存页面")
    private Boolean keepAlive;

    @Schema(description = "固定标签页")
    private Boolean affix;

    @Schema(description = "标签页是否允许关闭")
    private Boolean closable;

    @Schema(description = "子节点")
    private List<MenusModel> children;



}
