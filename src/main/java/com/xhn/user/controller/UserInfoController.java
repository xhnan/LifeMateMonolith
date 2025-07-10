package com.xhn.user.controller;

import com.xhn.base.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xhn
 * @since 2025-07-07
 */
@RestController
@RequestMapping("/user/userInfo")
public class UserInfoController {

    @GetMapping("/hello")
    public ResponseResult<String> hello() {
        return ResponseResult.success("Hello, User Info!");
    }

}
