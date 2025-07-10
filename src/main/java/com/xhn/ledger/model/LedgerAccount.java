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
 * 账户表，记录每个用户的账户信息
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Getter
@Setter
@ToString
@TableName("ledger_account")
public class LedgerAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，账户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 账户名称
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 账户类型（如：现金、银行、信用卡）
     */
    @TableField("account_type")
    private String accountType;

    /**
     * 账户余额
     */
    @TableField("balance")
    private BigDecimal balance;

    /**
     * 账户创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 账户更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
