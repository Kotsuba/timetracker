package com.touchsoft.timetracker.api.service;

import com.touchsoft.timetracker.api.dao.VacationDao;
import com.touchsoft.timetracker.api.model.Vacation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class VacationServiceImpl  implements VacationService {

    @Resource
    private VacationDao vacationDao;

    @Transactional
    public void addVacation(Vacation vacation) {
        vacationDao.insertVacation(vacation);
    }

    @Transactional(readOnly = true)
    public List<Vacation> getVacations(LocalDate date){
        return vacationDao.getVacations(date);
    }

    @Transactional
    public void editVacation(Vacation vacation) {
        vacationDao.editVacation(vacation);
    }

    @Transactional
    public void removeVacation(Vacation vacation) {
        vacationDao.deleteVacation(vacation);
    }

    @Transactional
    public boolean isVacation(long vacationId){
        return vacationDao.isVacation(vacationId);
    }

}
