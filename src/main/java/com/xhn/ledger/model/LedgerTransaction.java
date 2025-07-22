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
 * 交易表，记录用户的每一笔交易
 * </p>
 *
 * @author xhn
 * @since 2025-07-22
 */
@Getter
@Setter
@ToString
@TableName("ledger_transaction")
public class LedgerTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，交易ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 账户ID
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 交易金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 交易类型（如：支出、收入）
     */
    @TableField("transaction_type")
    private String transactionType;

    /**
     * 分类ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 交易发生时间
     */
    @TableField("date_time")
    private LocalDateTime dateTime;

    /**
     * 交易描述
     */
    @TableField("description")
    private String description;

    /**
     * 记录创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 记录更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
