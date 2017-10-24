package com.touchsoft.timetracker.api.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.touchsoft.timetracker.util.converter.LocalDateConverter;
import com.touchsoft.timetracker.util.converter.LocalDateDeserializer;
import com.touchsoft.timetracker.util.converter.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "user_vacation", catalog = "timetracker")
public class Vacation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uv_id")
    private Long id;

    @Column(name = "uv_user_id")
    private Long userId;

    @Column(name="uv_start_date")
    @Convert(converter = LocalDateConverter.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @Column(name="uv_end_date")
    @Convert(converter = LocalDateConverter.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    @Column(name="uv_duration")
    private int duration;

    @Column(name="uv_payment")
    private double payment;

    @Column(name="uv_pay_date")
    @Convert(converter = LocalDateConverter.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate payDate;

    @Column(name="uv_dayofftype")
    private int dayOffType;

    @Column(name="uv_comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long uv_user_id) {
        this.userId = uv_user_id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate uv_start_date) {
        this.startDate = uv_start_date;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate uv_end_date) {
        this.endDate = uv_end_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int uv_duration) {
        this.duration = uv_duration;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double uv_paument) {
        this.payment = uv_paument;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate uv_pay_date) {
        this.payDate = uv_pay_date;
    }

    public int getDayOffType() {
        return dayOffType;
    }

    public void setDayOffType(int uv_dayofftype) {
        this.dayOffType = uv_dayofftype;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String uv_comment) {
        this.comment = uv_comment;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id=" + id +
                ", userId=" + userId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", payment=" + payment +
                ", payDate=" + payDate +
                ", dayOffType=" + dayOffType +
                ", comment='" + comment + '\'' +
                '}';
    }

}
