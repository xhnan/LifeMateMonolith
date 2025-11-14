package com.xhn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.io.Serial;
import com.xhn.sys.model.base.BasePermissionsEntity;

@TableName("sys_permissions")
public class PermissionsEntity extends BasePermissionsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
