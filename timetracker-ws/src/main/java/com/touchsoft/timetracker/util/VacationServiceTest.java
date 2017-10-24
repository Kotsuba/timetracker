package com.touchsoft.timetracker.util;

import com.touchsoft.timetracker.api.dao.UserDao;
import com.touchsoft.timetracker.api.dao.UserNotFoundException;
import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.api.model.Vacation;
import com.touchsoft.timetracker.api.service.ReportingService;
import com.touchsoft.timetracker.api.service.UserService;
import com.touchsoft.timetracker.api.service.UtilService;
import com.touchsoft.timetracker.api.service.VacationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;


public class VacationServiceTest {
    static ApplicationContext ctr=new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
    static VacationService vacationService=(VacationService)ctr.getBean(VacationService.class);
    static ReportingService reportingService=(ReportingService) ctr.getBean(ReportingService.class);
    static UserDao userDao=(UserDao)ctr.getBean(UserDao.class);
    static UserService userService=(UserService)ctr.getBean(UserService.class);
    static UtilService utilService=ctr.getBean(UtilService.class);
    Vacation vacation=new Vacation();

    public void createVacation() {
        Vacation vacation=new Vacation();
        vacation.setUserId((long)3);
        vacation.setStartDate(LocalDate.now());
        vacation.setEndDate(LocalDate.now());
        vacation.setDuration(123);
        vacation.setDayOffType(1);
        vacationService.addVacation(vacation);
    }


    public void getUser() throws UserNotFoundException {
        User user=(User)userService.getUser("admin");
        System.out.println(user.toString());
    }


    public void updateVacation(){
        vacation.setComment("update");
        vacationService.editVacation(vacation);
    }

    public static void getVacation(){
        for (Vacation vacation:vacationService.getVacations(LocalDate.now())) {
            System.out.println(vacation.toString());
        }
    }

    public void deleteVacation(){
        Vacation vacation=new Vacation();
        vacation.setId((long)28);
        vacationService.removeVacation(vacation);
    }

    public static void isVacation(){
        System.out.println(vacationService.isVacation((long)1));
    }

    public static void getReportingByUserId(){
        System.out.println(reportingService.getReportByUserId(5L,LocalDate.now()));
    }

    public static void getReportingByUserIdAndDate(){
        System.out.println(reportingService.getReportByUserId(5L,LocalDate.of(2017,7,11)));
    }
}
