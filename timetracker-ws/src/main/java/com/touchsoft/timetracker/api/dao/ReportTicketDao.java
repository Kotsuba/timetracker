package com.touchsoft.timetracker.api.dao;

import com.touchsoft.timetracker.util.hibernate.CustomHibernateDaoSupport;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository(value = "reportTicketDao")
public class ReportTicketDao extends CustomHibernateDaoSupport {

    @Transactional(readOnly = true)
    public List getReportNewTickets() {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  u.u_name  AS name,\n" +
                        "  u.u_login AS login,\n" +
                        "  typeName AS ticketType,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "  COUNT(*) AS ticketCount,\n" +
                        "  SUM(reopenedCount) AS reopenedCount,\n" +
                        "  SUM(round(originalEsstimate / 3600, 2)) AS originalEsstimate ,\n" +
                        "  SUM(round(loggedTimeAssignee / 3600, 2)) AS loggedTimeAssignee,\n" +
                        "  SUM(round(loggedTimeQA / 3600, 2)) AS loggedTimeQA,\n" +
                        "  SUM(round(allLoggedTimeQA / 3600, 2)) AS allLoggedTimeQA,\n" +
                        "  SUM(round(loggedTimeDEV / 3600, 2))  AS loggedTimeDEV,\n" +
                        "  SUM(round(allLoggedTimeDEV / 3600, 2))  AS allLoggedTimeDEV,\n" +
                        "  SUM(round(loggedTimeOtherUser / 3600, 2)) AS loggedTimeOtherUser\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "  LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE loggedTimeAssignee!=0 GROUP BY u_login,ticketType");
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportNewTickets(String ticketType) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  u.u_name                                  AS name,\n" +
                        "  u.u_login                                 AS login,\n" +
                        "  typeName                                  AS ticketType,\n" +
                        "  COUNT(*)                                  AS ticketCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "  SUM(reopenedCount)                        AS reopenedCount,\n" +
                        "  SUM(round(originalEsstimate / 3600, 2))   AS originalEsstimate,\n" +
                        "  SUM(round(loggedTimeAssignee / 3600, 2))  AS loggedTimeAssignee,\n" +
                        "  SUM(round(loggedTimeQA / 3600, 2))        AS loggedTimeQA,\n" +
                        "  SUM(round(allLoggedTimeQA / 3600, 2))        AS allLoggedTimeQA,\n" +
                        "  SUM(round(loggedTimeDEV / 3600, 2))       AS loggedTimeDEV,\n" +
                        "  SUM(round(allLoggedTimeDEV / 3600, 2))       AS allLoggedTimeDEV,\n" +
                        "  SUM(round(loggedTimeOtherUser / 3600, 2)) AS loggedTimeOtherUser,\n" +
                        "  SUM(round(loggedTimeHelp / 3600, 2)) AS loggedTimeHelp\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "  LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE typeName =:ticketType\n" +
                        "GROUP BY u_login");
        query.setString("ticketType", ticketType);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportNewTicketsByLogin(String login) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  u.u_name                                  AS name,\n" +
                        "  u.u_login                                 AS login,\n" +
                        "  typeName                                  AS ticketType,\n" +
                        "  COUNT(*)                                  AS ticketCount,\n" +
                        "  SUM(reopenedCount)                        AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "  SUM(round(originalEsstimate / 3600, 2))   AS originalEsstimate,\n" +
                        "  SUM(round(loggedTimeAssignee / 3600, 2))  AS loggedTimeAssignee,\n" +
                        "  SUM(round(loggedTimeQA / 3600, 2))        AS loggedTimeQA,\n" +
                        "  SUM(round(allLoggedTimeQA / 3600, 2))        AS allLoggedTimeQA,\n" +
                        "  SUM(round(loggedTimeDEV / 3600, 2))       AS loggedTimeDEV,\n" +
                        "  SUM(round(allLoggedTimeDEV / 3600, 2))       AS allLoggedTimeDEV,\n" +
                        "  SUM(round(loggedTimeOtherUser / 3600, 2)) AS loggedTimeOtherUser,\n" +
                        "  SUM(round(loggedTimeHelp / 3600, 2)) AS loggedTimeHelp\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "  LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE u.u_login  =:login AND loggedTimeAssignee!=0\n" +
                        "GROUP BY u_login,ticketType");
        query.setString("login", login);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportNewForEachTicket(String login, String ticketType) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "u.u_name  AS name,\n" +
                        "u.u_login AS login,\n" +
                        "reopenedCount AS reopenedCount,\n" +
                        "ticket AS ticket,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "round(originalEsstimate / 3600, 2) AS originalEsstimate ,\n" +
                        "round(loggedTimeAssignee / 3600, 2) AS loggedTimeAssignee,\n" +
                        "round(loggedTimeQA / 3600, 2) AS loggedTimeQA,\n" +
                        "round(loggedTimeDEV / 3600, 2)  AS loggedTimeDEV,\n" +
                        "round(allLoggedTimeDEV / 3600, 2)       AS allLoggedTimeDEV,\n" +
                        "round(loggedTimeOtherUser / 3600, 2) AS loggedTimeOtherUser,\n" +
                        "round(loggedTimeHelp / 3600, 2) AS loggedTimeHelp\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE u_login=:login AND tt.typeName=:ticketType");
        query.setString("ticketType", ticketType);
        query.setString("login", login);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportNewByTicket(String ticket) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "u.u_name  AS name,\n" +
                        "u.u_login AS login,\n" +
                        "ticket AS ticket,\n" +
                        "typeName AS ticketType,\n" +
                        "reopenedCount AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "round(originalEsstimate / 3600, 2) AS originalEsstimate ,\n" +
                        "round(loggedTimeAssignee / 3600, 2) AS loggedTimeAssignee,\n" +
                        "round(loggedTimeQA / 3600, 2) AS loggedTimeQA,\n" +
                        "round(allLoggedTimeQA / 3600, 2) AS allLoggedTimeQA,\n" +
                        "round(loggedTimeDEV / 3600, 2)  AS loggedTimeDEV,\n" +
                        "round(loggedTimeOtherUser / 3600, 2) AS loggedTimeOtherUser,\n" +
                        "round(loggedTimeHelp / 3600, 2) AS loggedTimeHelp\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE ticket=:ticket");
        query.setString("ticket", ticket);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportNewByTicketType(String ticketType) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "u.u_name  AS name,\n" +
                        "u.u_login AS login,\n" +
                        "ticket AS ticket,\n" +
                        "typeName AS ticketType,\n" +
                        "reopenedCount AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "round(originalEsstimate / 3600, 2) AS originalEsstimate ,\n" +
                        "round(loggedTimeAssignee / 3600, 2) AS loggedTimeAssignee,\n" +
                        "round(loggedTimeQA / 3600, 2) AS loggedTimeQA,\n" +
                        "round(allLoggedTimeQA / 3600, 2) AS allLoggedTimeQA,\n" +
                        "round(loggedTimeDEV / 3600, 2)  AS loggedTimeDEV,\n" +
                        "round(allLoggedTimeDEV / 3600, 2) AS allLoggedTimeDEV,\n" +
                        "round(loggedTimeOtherUser / 3600, 2) AS loggedTimeOtherUser,\n" +
                        "round(loggedTimeHelp / 3600, 2) AS loggedTimeHelp\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE typeName=:ticketType");
        query.setString("ticketType", ticketType);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportNewAllByTicket() {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "u.u_name  AS name,\n" +
                        "u.u_login AS login,\n" +
                        "ticket AS ticket,\n" +
                        "typeName AS ticketType,\n" +
                        "reopenedCount AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "round(originalEsstimate / 3600, 2) AS originalEsstimate ,\n" +
                        "round(loggedTimeAssignee / 3600, 2) AS loggedTimeAssignee,\n" +
                        "round(loggedTimeQA / 3600, 2) AS loggedTimeQA,\n" +
                        "round(allLoggedTimeQA / 3600, 2) AS allLoggedTimeQA,\n" +
                        "round(loggedTimeDEV / 3600, 2)  AS loggedTimeDEV,\n" +
                        "round(allLoggedTimeDEV / 3600, 2) AS allLoggedTimeDEV,\n" +
                        "round(loggedTimeOtherUser / 3600, 2) AS loggedTimeOtherUser,\n" +
                        "round(loggedTimeHelp / 3600, 2) AS loggedTimeHelp\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "LEFT JOIN users AS u ON userId = u.u_id\n");
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getFullReportNewByTicket(String ticket) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  ticket,\n" +
                        "  typeName                                  AS ticketType,\n" +
                        "  SUM(reopenedCount)                        AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "  round(originalEsstimate / 3600, 2)   AS originalEsstimate,\n" +
                        "  SUM(round(loggedTimeAssignee / 3600, 2)) AS loggedTimeAssignee,\n" +
                        "  SUM(round(loggedTimeQA / 3600, 2))        AS loggedTimeQA,\n" +
                        "  round(allloggedTimeQA / 3600, 2) AS allLoggedTimeQA,\n" +
                        "  SUM(round(loggedTimeDEV / 3600, 2))      AS loggedTimeDEV,\n" +
                        "  round(allLoggedTimeDEV / 3600, 2) AS allLoggedTimeDEV,\n" +
                        "  SUM(round(loggedTimeOtherUser / 3600, 2)) AS loggedTimeOtherUser\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "  LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE ticket=:ticket");
        query.setString("ticket", ticket);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getFullReportNewByTicketType(String ticketType) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  ticket,\n" +
                        "  typeName                                  AS ticketType,\n" +
                        "  SUM(reopenedCount)                        AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "  round(originalEsstimate / 3600, 2)   AS originalEsstimate,\n" +
                        "  SUM(round(loggedTimeAssignee / 3600, 2)) AS loggedTimeAssignee,\n" +
                        "  SUM(round(loggedTimeQA / 3600, 2))        AS loggedTimeQA,\n" +
                        "  round(allloggedTimeQA / 3600, 2) AS allLoggedTimeQA,\n" +
                        "  SUM(round(loggedTimeDEV / 3600, 2))      AS loggedTimeDEV,\n" +
                        "  round(allLoggedTimeDEV / 3600, 2) AS allLoggedTimeDEV,\n" +
                        "  SUM(round(loggedTimeOtherUser / 3600, 2)) AS loggedTimeOtherUser\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "  LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "WHERE typeName=:ticketType GROUP BY ticket,ticketType");
        query.setString("ticketType", ticketType);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getFullReport() {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT\n" +
                        "  ticket,\n" +
                        "  typeName                                  AS ticketType,\n" +
                        "  SUM(reopenedCount)                        AS reopenedCount,\n" +
                        "reporter,\n" +
                        "resolution,\n" +
                        "  round(originalEsstimate / 3600, 2)   AS originalEsstimate,\n" +
                        "  SUM(round(loggedTimeAssignee / 3600, 2)) AS loggedTimeAssignee,\n" +
                        "  SUM(round(loggedTimeQA / 3600, 2))        AS loggedTimeQA,\n" +
                        "  round(allloggedTimeQA / 3600, 2) AS allLoggedTimeQA,\n" +
                        "  SUM(round(loggedTimeDEV / 3600, 2))      AS loggedTimeDEV,\n" +
                        "  round(allLoggedTimeDEV / 3600, 2) AS allLoggedTimeDEV,\n" +
                        "  SUM(round(loggedTimeOtherUser / 3600, 2)) AS loggedTimeOtherUser\n" +
                        "FROM tickets_report_new AS tr LEFT JOIN tickets_type AS tt ON tr.ticketType = tt.id\n" +
                        "  LEFT JOIN users AS u ON userId = u.u_id\n" +
                        "GROUP BY ticket,ticketType");
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
}
