package com.xhn.sys.enums;

import java.util.Optional;

/**
 * 菜单类型
 * 菜单
 * iframe
 * 外链
 * 按钮
 */
public enum MenuTypeEnum {

    MENU(1, "菜单"),
    IFRAME(2, "iframe"),
    OUTLINK(3, "外链"),
    BUTTON(4, "按钮");
    private final int value;
    private final String description;
    MenuTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
    // 如果找不到返回 null
    public static MenuTypeEnum fromValue(int value) {
        for (MenuTypeEnum e : values()) {
            if (e.value == value) {
                return e;
            }
        }
        return null;
    }

    // 返回 Optional\<MenuTypeEnum\>
    public static Optional<MenuTypeEnum> fromValueOptional(int value) {
        for (MenuTypeEnum e : values()) {
            if (e.value == value) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    // 如果找不到则抛出异常
    public static MenuTypeEnum fromValueOrThrow(int value) {
        MenuTypeEnum result = fromValue(value);
        if (result == null) {
            throw new IllegalArgumentException("Unknown MenuTypeEnum value: " + value);
        }
        return result;
    }
}
