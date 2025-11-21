package com.xhn.base;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author xhn
 * @date 2025/7/7 14:18
 * @description 统一返回结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    private boolean success;
    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;



    /**
     * 成功返回结果
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, true,"操作成功", data);
    }

    /**
     * 成功返回结果
     * @param message 提示信息
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(200, true,message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> failed(Integer errorCode, String message) {
        return new ResponseResult<>(errorCode, false,message, null);
    }

    /**
     * 失败返回结果
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(500,false, message, null);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> validateFailed(String message) {
        return new ResponseResult<>(400,false, message, null);
    }

    /**
     * 未授权返回结果
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> unauthorized() {
        return new ResponseResult<>(401,false, "暂未登录或token已过期", null);
    }

    /**
     * 未授权返回结果
     * @param message 提示信息
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> unauthorized(String message) {
        return new ResponseResult<>(401,false, message, null);
    }

    /**
     * 禁止访问返回结果
     * @param <T> 数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> forbidden() {
        return new ResponseResult<>(403, false,"没有相关权限", null);
    }
}