package com.xhn.sys.model;

import lombok.Data;

import java.util.List;

/**
 * @author xhn
 * @date 2025/11/26 16:54
 * @description
 */
@Data
public class RouterMeta {
    private String title;
    private String icon;
    private Integer rank;
    private List<String> roles;
    private List<String> auths;
}
