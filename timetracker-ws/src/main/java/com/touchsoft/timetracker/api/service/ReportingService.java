package com.touchsoft.timetracker.api.service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ReportingService {

    @Transactional(readOnly = true)
    List getReportByUserId(Long userId, LocalDate date);

    @Transactional(readOnly = true)
    List getReportByUserId(String metricName, LocalDate date, Long userId);

    @Transactional(readOnly = true)
    List getReportByMetric(String metricName, LocalDate date);

    @Transactional(readOnly = true)
    List getReportByMetric(String metricName, LocalDate date, Long teamId);

    @Transactional(readOnly = true)
    List getDates(LocalDate date);

    @Transactional(readOnly = true)
    List getUsers(LocalDate date);

    @Transactional(readOnly = true)
    List getUsers(LocalDate date, Long teamId);

    @Transactional(readOnly = true)
    List getReportMaxValue(String metricName, LocalDate date);

    @Transactional(readOnly = true)
    List getReportMaxValue(String metricName, LocalDate date, Long teamId);
}
