package com.touchsoft.timetracker.api.dao;


import com.touchsoft.timetracker.api.model.Vacation;
import com.touchsoft.timetracker.util.hibernate.CustomHibernateDaoSupport;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VacationDao extends CustomHibernateDaoSupport {

    @Transactional
    public void insertVacation(Vacation vacation) {
        getHibernateTemplate().save(vacation);
    }

    @Transactional(readOnly = true)
    public List<Vacation> getVacations(LocalDate date){
        Query query=getSessionFactory().getCurrentSession().
                createQuery("FROM Vacation WHERE month(startDate)=month(:date) and year(startDate)=year(:date)").
                setString("date",date.toString());
        return query.list();
    }

    @Transactional
    public void editVacation(Vacation vacation) {
        getHibernateTemplate().update(vacation);
    }

    @Transactional
    public void deleteVacation(Vacation vacation) {
        getHibernateTemplate().delete(vacation);
    }

    @Transactional
    public boolean isVacation(long id){
        Query query=getSessionFactory().getCurrentSession().
                createQuery("FROM Vacation WHERE id=:id").
                setLong("id",id);
        return !(query.list() == null || query.list().size() == 0);

    }
}
