package com.xhn.sys.controller;

import com.xhn.base.ResponseResult;
import com.xhn.sys.model.AppVersionModel;
import com.xhn.sys.model.SysAppVersionDTO;
import com.xhn.sys.service.SysAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * app更新日志表 前端控制器
 * </p>
 *
 * @author xhn
 * @since 2025-07-08
 */
@RestController
@RequestMapping("/sys/appVersion")
public class SysAppVersionController {

    @Autowired
    SysAppVersionService sysAppVersionService;

    //获取最新版本
    @GetMapping("/latestVersion")
    public ResponseResult<SysAppVersionDTO> latestVersion() {
        // 这里可以添加获取最新版本的逻辑

        SysAppVersionDTO result=  sysAppVersionService.checkVersion();
        return ResponseResult.success(result);

    }


}
