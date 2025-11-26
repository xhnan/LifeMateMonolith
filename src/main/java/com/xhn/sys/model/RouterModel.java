package com.xhn.sys.model;

import lombok.Data;

import java.util.List;

/**
 * @author xhn
 * @date 2025/11/26 16:52
 * @description
 */
@Data
public class RouterModel {

    private Long id;
    private String path;

    private String name;

    private  String component;

    private RouterMeta meta;

    private List<RouterModel> children;


}
