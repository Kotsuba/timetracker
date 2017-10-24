package com.touchsoft.timetracker.api.dao;

import com.touchsoft.timetracker.util.hibernate.CustomHibernateDaoSupport;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "utilDao")
public class UtilDao extends CustomHibernateDaoSupport {

    @Transactional(readOnly = true)
    public Boolean isColumnExistsInReporting(String column) {
        if (StringUtils.isNumeric(column)) {
            return false;
        } else {
            Query query = getSessionFactory().getCurrentSession().
                    createSQLQuery("SHOW FIELDS FROM reporting WHERE field=:column").
                    setString("column", column);
            try {
                query.list().get(0);
            } catch (IndexOutOfBoundsException ex) {
                return false;
            }
        }
        return true;

    }
}
