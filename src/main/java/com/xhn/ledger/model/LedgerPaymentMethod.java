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
 * 支付方式表，记录用户的支付方式（如现金、银行卡等）
 * </p>
 *
 * @author xhn
 * @since 2025-07-22
 */
@Getter
@Setter
@ToString
@TableName("ledger_payment_method")
public class LedgerPaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，支付方式ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 支付方式名称
     */
    @TableField("payment_method_name")
    private String paymentMethodName;

    /**
     * 支付方式创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 支付方式更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
