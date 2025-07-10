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
 * 通知表，记录与记账相关的提醒和通知
 * </p>
 *
 * @author xhn
 * @since 2025-07-10
 */
@Getter
@Setter
@ToString
@TableName("ledger_notification")
public class LedgerNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，通知ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 通知类型（如：预算提醒、账单提醒等）
     */
    @TableField("notification_type")
    private String notificationType;

    /**
     * 通知内容
     */
    @TableField("message")
    private String message;

    /**
     * 通知状态（未读、已读）
     */
    @TableField("status")
    private String status;

    /**
     * 通知创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 通知更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
