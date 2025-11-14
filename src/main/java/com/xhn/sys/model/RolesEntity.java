package com.xhn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.io.Serial;
import com.xhn.sys.model.base.BaseRolesEntity;

@TableName("sys_roles")
public class RolesEntity extends BaseRolesEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
