package com.touchsoft.timetracker.api.controller;

import com.touchsoft.timetracker.api.service.ReportTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/v2/report")
public class ReportTicketV2Controller {
    @Resource
    private ReportTicketService reportTicketService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List getReporting(@RequestParam(value = "type", required = false) String type) {
        if (type != null) {
            return reportTicketService.getReportNewTickets(type);
        }
        return reportTicketService.getReportNewTickets();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
    public List getReporting(@PathVariable(value = "login") String login, @RequestParam(value = "type", required = false) String type) {
        if (type != null) {
            return reportTicketService.getReportNewForEachTicket(login,type);
        }
        return reportTicketService.getReportNewTicketsByLogin(login);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ticket/{key}", method = RequestMethod.GET)
    public List getReportingByTicket(@PathVariable(value = "key") String key) {
        return reportTicketService.getReportNewByTicket(key);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ticket/all", method = RequestMethod.GET)
    public List getReportingAllByTicket(@RequestParam(value = "type", required = false) String type) {
        if (type != null) {
            return reportTicketService.getReportNewByTicketType(type);
        }
        return reportTicketService.getReportNewAllByTicket();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ticket/{key}/full", method = RequestMethod.GET)
    public List getReportingFullByTicket(@PathVariable(value = "key") String key) {
        return reportTicketService.getFullReportNewByTicket(key);
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ticket/full", method = RequestMethod.GET)
    public List getReportingFullByTicketType(@RequestParam(value = "type", required = false) String type) {
        if (type != null) {
            return reportTicketService.getFullReportNewByTicketType(type);
        }
        return reportTicketService.getFullReport();
    }
}
