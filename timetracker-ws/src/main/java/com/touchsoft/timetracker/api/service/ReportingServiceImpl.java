package com.touchsoft.timetracker.api.service;

import com.touchsoft.timetracker.api.dao.ReportDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService {

    @Resource
    private ReportDao reportingDao;

    @Override
    public List getReportByUserId(Long userId, LocalDate date) {
        return reportingDao.getReportByUserId(userId, date);
    }

    @Override
    public List getReportByMetric(String metricName, LocalDate date) {
        return reportingDao.getReportByMetric(metricName, date);
    }

    @Override
    public List getReportByMetric(String metricName, LocalDate date, Long teamId) {
        return reportingDao.getReportByMetric(metricName,date, teamId);
    }

    @Override
    public List getDates(LocalDate date) {
        return reportingDao.getDates(date);
    }

    @Override
    public List getUsers(LocalDate date) {
        return reportingDao.getUsers(date);
    }

    @Override
    public List getUsers(LocalDate date, Long teamId) {
        return reportingDao.getUsersByProject(date, teamId);
    }

    @Override
    public List getReportMaxValue(String metricName, LocalDate date) {
        return reportingDao.getReportMaxValue(metricName, date);
    }

    @Override
    public List getReportMaxValue(String metricName, LocalDate date, Long teamId) {
        return reportingDao.getReportMaxValue(metricName,date, teamId);
    }

    @Override
    public List getReportByUserId(String metricName, LocalDate date, Long userId){
        return reportingDao.getReportByUserId(metricName,date,userId);
    }
}
