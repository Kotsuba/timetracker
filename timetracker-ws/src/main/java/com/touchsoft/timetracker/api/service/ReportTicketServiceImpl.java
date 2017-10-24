package com.touchsoft.timetracker.api.service;

import com.touchsoft.timetracker.api.dao.ReportTicketDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportTicketServiceImpl implements ReportTicketService {

    @Resource
    private ReportTicketDao reportTicketDao;

    @Override
    public List getReportNewTickets() {
        return reportTicketDao.getReportNewTickets();
    }

    @Override
    public List getReportNewForEachTicket(String login, String ticketType) {
        return reportTicketDao.getReportNewForEachTicket(login,ticketType);
    }

    @Override
    public List getReportNewByTicket(String ticket) {
        return reportTicketDao.getReportNewByTicket(ticket);
    }

    @Override
    public List getReportNewTickets(String ticketType) {
        return reportTicketDao.getReportNewTickets(ticketType);
    }

    @Override
    public List getReportNewTicketsByLogin(String login) {
        return reportTicketDao.getReportNewTicketsByLogin(login);
    }

    @Override
    public List getFullReportNewByTicket(String ticket) {
        return reportTicketDao.getFullReportNewByTicket(ticket);
    }

    @Override
    public List getFullReportNewByTicketType(String ticketType) {
        return reportTicketDao.getFullReportNewByTicketType(ticketType);
    }

    @Override
    public List getFullReport() {
        return reportTicketDao.getFullReport();
    }

    @Override
    public List getReportNewAllByTicket() {
        return reportTicketDao.getReportNewAllByTicket();
    }

    @Override
    public List getReportNewByTicketType(String ticketType) {
        return reportTicketDao.getReportNewByTicketType(ticketType);
    }
}
