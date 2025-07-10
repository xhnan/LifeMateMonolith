package com.xhn.ledger.model;

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
 * 分类表，记录所有交易的分类信息（支持一级分类和二级分类）
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Getter
@Setter
@ToString
@TableName("ledger_category")
public class LedgerCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，分类ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 父类ID（如果是一级分类则为NULL）
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 分类类型（支出/收入）
     */
    @TableField("category_type")
    private String categoryType;

    /**
     * 分类创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 分类更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
