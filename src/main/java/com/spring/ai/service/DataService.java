package com.spring.ai.service;

import com.spring.ai.model.ChartData;
import com.spring.ai.model.DateUtil;
import com.spring.ai.model.QueryAnalysis;
import com.spring.ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private UserRepository userRepository;

    public ChartData processAnalysis(QueryAnalysis analysis) {
        LocalDateTime[] dateRange = DateUtil.calculateDateRange(
                analysis.getTimeFrame()
        );

        Long count = userRepository.countByCreationDateBetween(
                dateRange[0], dateRange[1]
        );

        return new ChartData()
                .setLabels(List.of("Registrations"))
                .setValues(List.of(count))
                .setChartType(analysis.getChartType())
                .setSummary(String.format(
                        "%d users registered in the last %d %s(s)",
                        count,
                        analysis.getTimeFrame().getValue(),
                        analysis.getTimeFrame().getPeriod().toLowerCase()
                ));
    }
}