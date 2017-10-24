package com.touchsoft.timetracker.api.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReportTicketService {

    @Transactional(readOnly = true)
    List getReportNewTickets();

    @Transactional(readOnly = true)
    List getReportNewForEachTicket(String login, String ticketType);

    @Transactional(readOnly = true)
    List getReportNewByTicket(String ticket);

    @Transactional(readOnly = true)
    List getReportNewTickets(String ticketType);

    @Transactional(readOnly = true)
    List getReportNewTicketsByLogin(String login);

    @Transactional(readOnly = true)
    List getFullReportNewByTicket(String ticket);

    @Transactional(readOnly = true)
    List getFullReportNewByTicketType(String ticketType);

    @Transactional(readOnly = true)
    List getFullReport();

    @Transactional(readOnly = true)
    List getReportNewAllByTicket();

    @Transactional(readOnly = true)
    List getReportNewByTicketType(String ticketType);
}
