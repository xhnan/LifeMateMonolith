package com.xhn.user.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhn
 * @since 2025-07-07
 */
@Getter
@Setter
@ToString
@TableName("user_profile_tag")
public class UserProfileTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("profile_id")
    private Long profileId;

    @TableField("tag_id")
    private Long tagId;

    @TableId("id")
    private Long id;
}
