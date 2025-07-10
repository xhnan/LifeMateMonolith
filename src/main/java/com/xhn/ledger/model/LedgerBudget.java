package com.xhn.ledger.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 预算表，记录用户为不同分类设置的预算
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Getter
@Setter
@ToString
@TableName("ledger_budget")
public class LedgerBudget implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，预算ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 分类ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 预算金额
     */
    @TableField("budget_amount")
    private BigDecimal budgetAmount;

    /**
     * 预算开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 预算结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 预算创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 预算更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
