package com.xhn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.io.Serial;
import com.xhn.sys.model.base.BaseAppVersionEntity;

@TableName("sys_app_version")
public class AppVersionEntity extends BaseAppVersionEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
