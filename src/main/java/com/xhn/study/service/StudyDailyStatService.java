package com.xhn.study.service;

import com.xhn.study.model.StudyDailyStat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhn.study.model.StudyDailyStatDTO;

import java.util.List;

/**
 * <p>
 * 学习每日题量统计表 服务类
 * </p>
 *
 * @author xhn
 * @since 2025-07-30
 */
public interface StudyDailyStatService extends IService<StudyDailyStat> {
    List<StudyDailyStatDTO> getRecentStats(Long userId, int days);
    StudyDailyStatDTO getTodayStats(Long userId);
    boolean addDailyStat(StudyDailyStatDTO statDTO);
}
