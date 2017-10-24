package com.touchsoft.timetracker.api.dao;

import com.touchsoft.timetracker.util.hibernate.CustomHibernateDaoSupport;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository(value = "reportingDao")
public class ReportDao extends CustomHibernateDaoSupport {

    @Transactional(readOnly = true)
    public List getReportByUserId(long userId, LocalDate date) {
        Query query = getSessionFactory().getCurrentSession().
                createQuery("FROM Report WHERE userId=:userId and date>=:date ORDER BY userId,date").
                setLong("userId", userId).
                setString("date", date.toString());
        return query.list();
    }
     /*this method return date in specific format
    "dateRun": {
        "year": 2017,
                "month": "JULY",
                "dayOfMonth": 27,
                "monthValue": 7,
                "dayOfWeek": "THURSDAY",
                "era": "CE",
                "dayOfYear": 208,
                "leapYear": false,
                "chronology": {
                    "id": "ISO",
                    "calendarType": "iso8601"
        }
    }*/
    /*@Transactional(readOnly = true)
    public List getReportByMetric(String metricName) {
        Criteria cr = getSessionFactory().getCurrentSession().createCriteria(Report.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("date").as("dateRun"))
                        .add(Projections.property("userId").as("userId"))
                        .add(Projections.property(metricName).as("progress")));
        return cr.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }*/

    @Transactional(readOnly = true)
    public List getDates(LocalDate date) {
        String sql = String.format("SELECT DISTINCT (rp_date_run)\n" +
                "FROM reporting\n" +
                "WHERE rp_date_run >= '%s'\n" +
                "ORDER BY rp_date_run;", date);
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(sql);
        return query.list();
    }

    @Transactional(readOnly = true)
    public List getUsers(LocalDate date) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT DISTINCT " +
                        "rp_u_id AS id," +
                        "u_login AS login," +
                        "u_name AS name " +
                        "FROM reporting LEFT JOIN users ON rp_u_id = u_id " +
                        "WHERE rp_date_run >=:date " +
                        "ORDER BY u_name;").
                setString("date", date.toString());
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportByMetric(String metricName, LocalDate date) {
        String sql = String.format("SELECT\n" +
                "  rp_date_run         AS date,\n" +
                "  rp_u_id             AS userId,\n" +
                "  %s                  AS progress\n" +
                "FROM reporting LEFT JOIN users ON rp_u_id=u_id\n" +
                "WHERE rp_date_run >= '%s'\n" +
                "ORDER BY u_name, rp_date_run;", metricName, date);
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(sql);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportByMetric(String metricName, LocalDate date, Long teamId) {
        String sql = String.format("SELECT\n" +
                "  rp_date_run         AS date,\n" +
                "  rp_u_id             AS userId,\n" +
                "  %s                  AS progress\n" +
                "FROM reporting\n" +
                "  LEFT JOIN user_bind ON rp_u_id = ub_id_u LEFT JOIN users ON rp_u_id=u_id\n" +
                "WHERE rp_date_run >= '%s' AND ub_id_p = %s AND ub_checked = 1\n" +
                "ORDER BY u_name, rp_date_run;", metricName, date, teamId);
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(sql);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportMaxValue(String metricName, LocalDate date) {
        String sql = String.format("SELECT\n" +
                "  rp_date_run              AS date,\n" +
                "  rp_u_id                  AS userId,\n" +
                "  MAX(%s)                  AS progress\n" +
                "FROM reporting\n" +
                "WHERE rp_date_run >= '%s'\n" +
                "GROUP BY rp_date_run\n" +
                "ORDER BY rp_date_run;", metricName, date);
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(sql);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportByUserId(String metricName, LocalDate date, Long userId) {
        String sql = String.format("SELECT\n" +
                "  rp_date_run         AS date,\n" +
                "  rp_u_id             AS userId,\n" +
                "  %s                  AS progress\n" +
                "FROM reporting\n" +
                "WHERE rp_date_run >= '%s' AND rp_u_id = %s\n" +
                "ORDER BY rp_date_run;", metricName, date, userId);
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(sql);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getUsersByProject(LocalDate date, Long teamId) {
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("SELECT DISTINCT " +
                        "rp_u_id AS id," +
                        "u_login AS login," +
                        "u_name AS name " +
                        "FROM reporting " +
                        "LEFT JOIN users ON rp_u_id = u_id " +
                        "LEFT JOIN user_bind  ON rp_u_id = ub_id_u " +
                        "WHERE rp_date_run >= :date " +
                        "AND ub_id_p = :teamId " +
                        "AND ub_checked = 1 " +
                        "ORDER BY u_name;").
                setString("date", date.toString()).
                setLong("teamId", teamId);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Transactional(readOnly = true)
    public List getReportMaxValue(String metricName, LocalDate date, Long projectId) {
        String sql = String.format("SELECT\n" +
                "  rp_date_run              AS date,\n" +
                "  rp_u_id                  AS userId,\n" +
                "  MAX(%s)                  AS progress\n" +
                "FROM reporting\n" +
                "  LEFT JOIN user_bind ON rp_u_id = ub_id_u\n" +
                "WHERE rp_date_run >= '%s' and ub_id_p=%s AND\n" +
                "      ub_checked = 1\n" +
                "GROUP BY rp_date_run\n" +
                "ORDER BY rp_date_run;", metricName, date, projectId);
        Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(sql);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

}
