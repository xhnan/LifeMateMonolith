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
 * 账单表，记录用户的账单信息
 * </p>
 *
 * @author xhn
 * @since 2025-07-22
 */
@Getter
@Setter
@ToString
@TableName("ledger_bill")
public class LedgerBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，账单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 账单名称
     */
    @TableField("bill_name")
    private String billName;

    /**
     * 账单金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 账单到期时间
     */
    @TableField("due_time")
    private LocalDateTime dueTime;

    /**
     * 账单状态（如：未支付、已支付）
     */
    @TableField("status")
    private String status;

    /**
     * 账单创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 账单更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
