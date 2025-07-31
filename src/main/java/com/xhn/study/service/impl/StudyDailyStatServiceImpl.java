package com.xhn.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhn.study.mapper.StudyDailyStatMapper;
import com.xhn.study.model.StudyDailyStat;
import com.xhn.study.model.StudyDailyStatDTO;
import com.xhn.study.service.StudyDailyStatService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyDailyStatServiceImpl extends ServiceImpl<StudyDailyStatMapper, StudyDailyStat> implements StudyDailyStatService {

    @Override
    public List<StudyDailyStatDTO> getRecentStats(Long userId, int days) {
        LocalDate startDate = LocalDate.now().minusDays(days - 1);
        LocalDate endDate = LocalDate.now();

        LambdaQueryWrapper<StudyDailyStat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudyDailyStat::getUserId, userId)
                .between(StudyDailyStat::getStatDate, startDate, endDate)
                .orderByAsc(StudyDailyStat::getStatDate);

        List<StudyDailyStat> statList = this.list(queryWrapper);
        return statList.stream()
                .map(stat -> {
                    StudyDailyStatDTO dto = new StudyDailyStatDTO();
                    dto.setId(stat.getId());
                    dto.setUserId(stat.getUserId());
                    dto.setStatDate(stat.getStatDate());
                    dto.setTotal(stat.getTotal());
                    dto.setCorrect(stat.getCorrect());
                    dto.setCategory(stat.getCategory()); // 添加分类字段
                    // 移除 timeSpent 相关代码
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public StudyDailyStatDTO getTodayStats(Long userId) {
        LocalDate today = LocalDate.now();

        LambdaQueryWrapper<StudyDailyStat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudyDailyStat::getUserId, userId)
                .eq(StudyDailyStat::getStatDate, today);

        StudyDailyStat todayStat = this.getOne(queryWrapper);

        if (todayStat == null) {
            StudyDailyStatDTO emptyDto = new StudyDailyStatDTO();
            emptyDto.setUserId(userId);
            emptyDto.setStatDate(today);
            emptyDto.setTotal(0);
            emptyDto.setCorrect(0);
            // 移除 timeSpent 相关代码
            return emptyDto;
        }

        StudyDailyStatDTO dto = new StudyDailyStatDTO();
        dto.setId(todayStat.getId());
        dto.setUserId(todayStat.getUserId());
        dto.setStatDate(todayStat.getStatDate());
        dto.setTotal(todayStat.getTotal());
        dto.setCorrect(todayStat.getCorrect());
        dto.setCategory(todayStat.getCategory()); // 添加分类字段
        // 移除 timeSpent 相关代码
        return dto;
    }

    @Override
    public boolean addDailyStat(StudyDailyStatDTO statDTO) {
        if (statDTO.getStatDate() == null) {
            statDTO.setStatDate(LocalDate.now());
        }

        LambdaQueryWrapper<StudyDailyStat> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudyDailyStat::getUserId, statDTO.getUserId())
                .eq(StudyDailyStat::getStatDate, statDTO.getStatDate());

        StudyDailyStat existingStat = this.getOne(queryWrapper);

        if (existingStat != null) {
            existingStat.setTotal(existingStat.getTotal() + statDTO.getTotal());
            existingStat.setCorrect(existingStat.getCorrect() + statDTO.getCorrect());
            existingStat.setCategory(statDTO.getCategory()); // 添加分类字段
            // 移除 timeSpent 相关代码
            return updateById(existingStat);
        } else {
            StudyDailyStat newStat = new StudyDailyStat();
            newStat.setId(null); // 确保添加时ID为null
            newStat.setUserId(statDTO.getUserId());
            newStat.setStatDate(statDTO.getStatDate());
            newStat.setTotal(statDTO.getTotal());
            newStat.setCorrect(statDTO.getCorrect());
            newStat.setCategory(statDTO.getCategory()); // 添加分类字段
            newStat.setCreatedTime(LocalDateTime.now()); // 设置创建时间
            newStat.setUpdatedTime(LocalDateTime.now()); // 设置更新时间
            // 移除 timeSpent 相关代码
            return save(newStat);
        }
    }
}