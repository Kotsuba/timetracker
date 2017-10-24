package com.touchsoft.timetracker.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.touchsoft.timetracker.util.converter.LocalDateConverter;
import com.touchsoft.timetracker.util.converter.LocalDateDeserializer;
import com.touchsoft.timetracker.util.converter.LocalDateSerializer;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "reporting", catalog = "timetracker")
public class Report implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rp_id")
    private Long id;

    @Column(name = "rp_date_run")
    @Convert(converter = LocalDateConverter.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @Column(name = "rp_u_id")
    private Long userId;

    @Column(name = "rp_inprogress_count")
    private Integer inProgressTicketCount;

    @Column(name = "rp_closed_count")
    private Integer closedTicketCount;

    @Column(name = "rp_fixed_count")
    private Integer fixedTicketCount;

    @Column(name = "rp_reopened_count")
    private Integer reopenedTicketCount;

    @Column(name = "rp_fixed_closed_count")
    private Float fixedClosedTicketCount;

    @Column(name = "rp_fixed_closed_time")
    private Float fixedClosedTicketTime;

    @Column(name = "rp_inprogress_all_count")
    private Float inProgressAllTicketCount;

    @Column(name = "rp_inprogress_all_time")
    private Float inProgressAllTicketTime;

    @Column(name = "rp_avg_time_resolved")
    private Float avgTimeResolvedTicket;

    @Column(name = "rp_avg_time_unresolved")
    private Float avgTimeUnResolvedTicket;

    @Column(name = "rp_inprogress_logged_time")
    private Float inProgressLoggedTime;

    @Column(name = "rp_ticket_full_time")
    private Float ticketFullTime;

    @Column(name = "rp_user_full_time")
    private Float userFullTime;

    @Column(name = "rp_jira_timetracker_time")
    private Float jiraTimeTrackerTime;

    @Column(name = "rp_door_timetracker_time")
    private Float doorTimeTrackerTime;

    @Column(name = "rp_tickets")
    private String tickets;

    @Column(name = "rp_worked_days")
    private Integer workedDays;

    @Column(name = "rp_worked_dates")
    private String workedDates;

    @Column(name = "rp_fixed_ticket_original_estimate")
    private Float fixedTicketTimeOriginalEstimate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getInProgressTicketCount() {
        return inProgressTicketCount;
    }

    public void setInProgressTicketCount(Integer inProgressTicketCount) {
        this.inProgressTicketCount = inProgressTicketCount;
    }

    public Integer getClosedTicketCount() {
        return closedTicketCount;
    }

    public void setClosedTicketCount(Integer closedTicketCount) {
        this.closedTicketCount = closedTicketCount;
    }

    public Integer getFixedTicketCount() {
        return fixedTicketCount;
    }

    public void setFixedTicketCount(Integer fixedTicketCount) {
        this.fixedTicketCount = fixedTicketCount;
    }

    public Integer getReopenedTicketCount() {
        return reopenedTicketCount;
    }

    public void setReopenedTicketCount(Integer reopenedTicketCount) {
        this.reopenedTicketCount = reopenedTicketCount;
    }

    public Float getFixedClosedTicketCount() {
        return fixedClosedTicketCount;
    }

    public void setFixedClosedTicketCount(Float fixedClosedTicketCount) {
        this.fixedClosedTicketCount = fixedClosedTicketCount;
    }

    public Float getFixedClosedTicketTime() {
        return fixedClosedTicketTime;
    }

    public void setFixedClosedTicketTime(Float fixedClosedTicketTime) {
        this.fixedClosedTicketTime = fixedClosedTicketTime;
    }

    public Float getInProgressAllTicketCount() {
        return inProgressAllTicketCount;
    }

    public void setInProgressAllTicketCount(Float inProgressAllTicketCount) {
        this.inProgressAllTicketCount = inProgressAllTicketCount;
    }

    public Float getInProgressAllTicketTime() {
        return inProgressAllTicketTime;
    }

    public void setInProgressAllTicketTime(Float inProgressAllTicketTime) {
        this.inProgressAllTicketTime = inProgressAllTicketTime;
    }

    public Float getAvgTimeResolvedTicket() {
        return avgTimeResolvedTicket;
    }

    public void setAvgTimeResolvedTicket(Float avgTimeResolvedTicket) {
        this.avgTimeResolvedTicket = avgTimeResolvedTicket;
    }

    public Float getAvgTimeUnResolvedTicket() {
        return avgTimeUnResolvedTicket;
    }

    public void setAvgTimeUnResolvedTicket(Float avgTimeUnResolvedTicket) {
        this.avgTimeUnResolvedTicket = avgTimeUnResolvedTicket;
    }

    public Float getInProgressLoggedTime() {
        return inProgressLoggedTime;
    }

    public void setInProgressLoggedTime(Float inProgressLoggedTime) {
        this.inProgressLoggedTime = inProgressLoggedTime;
    }

    public Float getTicketFullTime() {
        return ticketFullTime;
    }

    public void setTicketFullTime(Float ticketFullTime) {
        this.ticketFullTime = ticketFullTime;
    }

    public Float getUserFullTime() {
        return userFullTime;
    }

    public void setUserFullTime(Float userFullTime) {
        this.userFullTime = userFullTime;
    }

    public Float getJiraTimeTrackerTime() {
        return jiraTimeTrackerTime;
    }

    public void setJiraTimeTrackerTime(Float jiraTimeTrackerTime) {
        this.jiraTimeTrackerTime = jiraTimeTrackerTime;
    }

    public Float getDoorTimeTrackerTime() {
        return doorTimeTrackerTime;
    }

    public void setDoorTimeTrackerTime(Float doorTimeTrackerTime) {
        this.doorTimeTrackerTime = doorTimeTrackerTime;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public Integer getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(Integer workedDays) {
        this.workedDays = workedDays;
    }

    public String getWorkedDates() {
        return workedDates;
    }

    public void setWorkedDates(String workedDates) {
        this.workedDates = workedDates;
    }

    public Float getFixedTicketTimeOriginalEstimate() {
        return fixedTicketTimeOriginalEstimate;
    }

    public void setFixedTicketTimeOriginalEstimate(Float fixedTicketTimeOriginalEstimate) {
        this.fixedTicketTimeOriginalEstimate = fixedTicketTimeOriginalEstimate;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", inProgressTicketCount=" + inProgressTicketCount +
                ", closedTicketCount=" + closedTicketCount +
                ", fixedTicketCount=" + fixedTicketCount +
                ", reopenedTicketCount=" + reopenedTicketCount +
                ", fixedClosedTicketCount=" + fixedClosedTicketCount +
                ", fixedClosedTicketTime=" + fixedClosedTicketTime +
                ", inProgressAllTicketCount=" + inProgressAllTicketCount +
                ", inProgressAllTicketTime=" + inProgressAllTicketTime +
                ", avgTimeResolvedTicket=" + avgTimeResolvedTicket +
                ", avgTimeUnResolvedTicket=" + avgTimeUnResolvedTicket +
                ", inProgressLoggedTime=" + inProgressLoggedTime +
                ", ticketFullTime=" + ticketFullTime +
                ", userFullTime=" + userFullTime +
                ", jiraTimeTrackerTime=" + jiraTimeTrackerTime +
                ", doorTimeTrackerTime=" + doorTimeTrackerTime +
                ", tickets='" + tickets + '\'' +
                ", workedDays=" + workedDays +
                ", workedDates='" + workedDates + '\'' +
                '}';
    }
}
