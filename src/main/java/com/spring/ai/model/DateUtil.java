package com.spring.ai.model;

import java.time.LocalDateTime;

public class DateUtil {

    public static LocalDateTime[] calculateDateRange(TimeFrame timeFrame) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start;

        switch (timeFrame.getPeriod().toUpperCase()) {
            case "DAY":
                start = now.minusDays(timeFrame.getValue());
                break;
            case "WEEK":
                start = now.minusWeeks(timeFrame.getValue());
                break;
            case "MONTH":
                start = now.minusMonths(timeFrame.getValue());
                break;
            default:
                throw new IllegalArgumentException("Invalid time period: " + timeFrame.getPeriod());
        }

        return new LocalDateTime[]{start, now};
    }
}
