package com.touchsoft.timetracker.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "user_bind",catalog = "timetracker")
public class UserTeam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "ub_id")
    private Long id;

    @JsonIgnore
    @Column(name = "ub_rate")
    private Float rate;
    @JsonIgnore
    @Column(name = "ub_checked")
    private Boolean checked;

    @ManyToOne
    @JoinColumn(name = "ub_id_u")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ub_id_p")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "UserTeam{" +
                "id=" + id +
                ", user=" + user +
                ", rate=" + rate +
                ", checked=" + checked +
                ", team=" + team +
                '}';
    }
}
