package com.xhn.study.controller;

import com.xhn.base.ResponseResult;
import com.xhn.study.model.StudyDailyStatDTO;
import com.xhn.study.service.StudyDailyStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学习每日题量统计表 前端控制器
 * </p>
 *
 * @author xhn
 * @since 2025-07-30
 */
@RestController
@RequestMapping("/study/dailyStat")
public class StudyDailyStatController {
    @Autowired
    private StudyDailyStatService studyDailyStatService;

    /**
     * 查询近10天的学习统计数据
     */
    @GetMapping("/recent")
    public ResponseResult<List<StudyDailyStatDTO>> getRecentStats(@CurrentSecurityContext SecurityContext securityContext) {
        try {
            // 从安全上下文获取用户ID
            Long userId =Long.valueOf( securityContext.getAuthentication().getPrincipal().toString());
            // 获取近10天的数据
            List<StudyDailyStatDTO> statList = studyDailyStatService.getRecentStats(userId, 10);
            return ResponseResult.success(statList);
        } catch (Exception e) {
            return ResponseResult.error("获取近期学习统计数据失败：" + e.getMessage());
        }
    }

    /**
     * 查询今天的学习统计数据
     */
    @GetMapping("/today")
    public ResponseResult<StudyDailyStatDTO> getTodayStats(@CurrentSecurityContext SecurityContext securityContext) {
        try {
            Long userId =Long.valueOf( securityContext.getAuthentication().getPrincipal().toString());
            StudyDailyStatDTO todayStat = studyDailyStatService.getTodayStats(userId);
            return ResponseResult.success(todayStat);
        } catch (Exception e) {
            return ResponseResult.error("获取今日学习统计数据失败：" + e.getMessage());
        }
    }

    /**
     * 添加学习统计数据
     */
    @PostMapping("/add")
    public ResponseResult<Boolean> addDailyStat(@RequestBody StudyDailyStatDTO statDTO,@CurrentSecurityContext SecurityContext securityContext) {
        try {
            // 设置当前用户ID

            statDTO.setUserId(Long.valueOf( securityContext.getAuthentication().getPrincipal().toString()));
            boolean result = studyDailyStatService.addDailyStat(statDTO);
            return ResponseResult.success(result);
        } catch (Exception e) {
            return ResponseResult.error("添加学习统计数据失败：" + e.getMessage());
        }
    }

    /**
     * 获取当前登录用户ID
     */
    private Long getUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("未登录或登录已过期");
        }
        String userId = authentication.getPrincipal().toString();
        return Long.valueOf(userId);
    }
}
