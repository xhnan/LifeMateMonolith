package com.xhn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.io.Serial;
import com.xhn.sys.model.base.BaseMenusEntity;

@TableName("sys_menus")
public class MenusEntity extends BaseMenusEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}
