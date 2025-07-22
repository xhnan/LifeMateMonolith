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
 * 账户变动日志表，记录账户余额变动的详细信息
 * </p>
 *
 * @author xhn
 * @since 2025-07-22
 */
@Getter
@Setter
@ToString
@TableName("ledger_account_change_log")
public class LedgerAccountChangeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，变动日志ID
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
     * 变动类型（如：收入、支出、转账等）
     */
    @TableField("change_type")
    private String changeType;

    /**
     * 变动金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 变动后的账户余额
     */
    @TableField("balance_after_change")
    private BigDecimal balanceAfterChange;

    /**
     * 变动描述
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
