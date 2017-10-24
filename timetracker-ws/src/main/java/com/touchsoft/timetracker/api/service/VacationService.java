package com.touchsoft.timetracker.api.service;

import com.touchsoft.timetracker.api.model.Vacation;

import java.time.LocalDate;
import java.util.List;

public interface VacationService {

    void addVacation(Vacation vacation);

    List<Vacation> getVacations(LocalDate date);

    void editVacation(Vacation vacation);

    void removeVacation(Vacation vacation);

    boolean isVacation(long vacationId);
}
