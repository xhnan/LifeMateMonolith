package com.xhn.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 学习每日题量统计表
 * </p>
 *
 * @author xhn
 * @since 2025-07-30
 */
@Getter
@Setter
@ToString
@TableName("study_daily_stat")
public class StudyDailyStat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 统计日期
     */
    @TableField("stat_date")
    private LocalDate statDate;

    /**
     * 题目分类
     */
    @TableField("category")
    private String category;

    /**
     * 做题总数
     */
    @TableField("total")
    private Integer total;

    /**
     * 做对数量
     */
    @TableField("correct")
    private Integer correct;

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
