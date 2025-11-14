package com.xhn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.io.Serial;
import com.xhn.sys.model.base.BaseUsersEntity;

@TableName("sys_users")
public class UsersEntity extends BaseUsersEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
