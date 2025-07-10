package com.xhn.auth.controller;

import com.xhn.auth.model.AuthAccountDTO;
import com.xhn.auth.service.AuthAccountService;
import com.xhn.base.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xhn
 * @since 2025-07-07
 */
@RestController
@RequestMapping("/auth/account")
@Tag(name = "账户管理", description = "账户注册、登录等操作")
public class AuthAccountController {


    @Autowired
    AuthAccountService authAccountService;


    /**
     * 注册
     * @param authAccountDTO
     * @return
     */
    @Operation(
            summary = "用户注册",
            description = "创建新用户账户",
            responses = {
                    @ApiResponse(responseCode = "200", description = "注册成功"),
                    @ApiResponse(responseCode = "400", description = "请求参数错误")
            }
    )
    @Parameter(name = "authAccountDTO", description = "用户账户信息", required = true)
    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody AuthAccountDTO authAccountDTO) {
        // 这里可以添加注册逻辑
        authAccountService.register(authAccountDTO);
        return ResponseResult.success("注册成功");
    }

    @GetMapping("/validate")
    public ResponseResult<Boolean> validate() {
        // 验证Token是否有效
        return ResponseResult.success(true);
    }


    //登录
    @Operation(
            summary = "用户登录",
            description = "用户使用用户名和密码登录",
            responses = {
                    @ApiResponse(responseCode = "200", description = "登录成功"),
                    @ApiResponse(responseCode = "401", description = "未授权或登录失败")
            }
    )
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)
    })
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody AuthAccountDTO authAccountDTO) {
        // 这里可以添加登录逻辑
        // 如果登录成功，返回一个Token
        String token = authAccountService.login(authAccountDTO);
        if (token != null) {
            return ResponseResult.success(token);
        } else {
            return ResponseResult.error("登录失败，用户名或密码错误");
        }
    }


}
