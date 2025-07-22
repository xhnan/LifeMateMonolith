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
 * 标签表，记录用户在交易中添加的标签
 * </p>
 *
 * @author xhn
 * @since 2025-07-22
 */
@Getter
@Setter
@ToString
@TableName("ledger_tag")
public class LedgerTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，标签ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 标签名称
     */
    @TableField("tag_name")
    private String tagName;

    /**
     * 标签创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 标签更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
