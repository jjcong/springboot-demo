package com.jincong.springboot.config;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * TimeType
 *
 * @author j_cong
 * @version V1.0
 * @date 2021/6/13
 */
public enum TimeType {

    SECONDS(Duration::ofSeconds),
    MINUTES(Duration::ofMinutes),
    HOURS(Duration::ofHours),
    DAY(Duration::ofDays),
    WEEK(week -> {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, now.plusWeeks(week));
    }),
    MONTH(month -> {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, now.plusMonths(month));
    }),
    YEAR(year -> {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, now.plusYears(year));
    });

    TimeType(Function<Long, Duration> duration) {
        this.duration = duration;
    }

    public Function<Long, Duration> duration;

}
