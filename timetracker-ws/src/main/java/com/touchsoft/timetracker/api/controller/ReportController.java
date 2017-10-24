package com.touchsoft.timetracker.api.controller;

import com.touchsoft.timetracker.api.dao.MetricNotFoundException;
import com.touchsoft.timetracker.api.service.ReportingService;
import com.touchsoft.timetracker.api.service.UtilService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {

    @Resource
    private ReportingService reportingService;
    @Resource
    private UtilService utilService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List getReporting(@RequestParam(value = "metric") String metricName,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date,
                             @RequestParam(value = "project", required = false) Long projectId) {

        if(!utilService.isColumnExistsInReporting(metricName)){
            throw new MetricNotFoundException();
        }
        if (date != null && projectId != null) {
            return reportingService.getReportByMetric(metricName, date, projectId);
        }
        if (date != null) {
            return reportingService.getReportByMetric(metricName, date);
        }
        if(projectId!=null){
            return reportingService.getReportByMetric(metricName, LocalDate.now().minusWeeks(1L), projectId);
        }
        return reportingService.getReportByMetric(metricName, LocalDate.now().minusWeeks(1L));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/max", method = RequestMethod.GET)
    public List getReportingMaxValue(@RequestParam(value = "metric") String metricName,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date,
                                     @RequestParam(value = "project", required = false) Long projectId) {
        if(!utilService.isColumnExistsInReporting(metricName)){
            throw new MetricNotFoundException();
        }
        if (date != null && projectId != null) {
            return reportingService.getReportMaxValue(metricName, date, projectId);
        }
        if (date != null) {
            return reportingService.getReportMaxValue(metricName, date);
        }
        if(projectId!=null){
            return reportingService.getReportMaxValue(metricName, LocalDate.now().minusWeeks(1L), projectId);
        }
        return reportingService.getReportMaxValue(metricName, LocalDate.now().minusWeeks(1L));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public List getReportingByUserId(@PathVariable(value = "userId") long userId,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date,
                                     @RequestParam(value = "metric", required = false) String metricName) {
        if (date != null && metricName != null) {
            if(!utilService.isColumnExistsInReporting(metricName)){
                throw new MetricNotFoundException();
            }else {
                return reportingService.getReportByUserId(metricName, date, userId);
            }
        }
        if (metricName != null) {
            if(!utilService.isColumnExistsInReporting(metricName)){
                throw new MetricNotFoundException();
            }else {
                return reportingService.getReportByUserId(metricName, LocalDate.now().minusWeeks(1L), userId);
            }
        }
        if (date != null) {
            return reportingService.getReportByUserId(userId, date);
        }
        return reportingService.getReportByUserId(userId, LocalDate.now().minusWeeks(1L));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public List getDateRun(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date) {
        if (date != null) {
            return reportingService.getDates(date);
        }
        return reportingService.getDates(LocalDate.now().minusWeeks(1L));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List getUserName(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "date", required = false) LocalDate date,
                            @RequestParam(value = "project", required = false) Long projectId) {
        if (date != null && projectId != null) {
            return reportingService.getUsers(date, projectId);
        }
        if (date != null) {
            return reportingService.getUsers(date);
        }
        if (projectId != null) {
            return reportingService.getUsers(LocalDate.now().minusWeeks(1L), projectId);
        }
        return reportingService.getUsers(LocalDate.now().minusWeeks(1L));
    }


}
