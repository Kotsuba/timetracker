package com.touchsoft.timetracker.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users", catalog = "timetracker")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "u_id")
    private Long id;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_manager_id")
    private Long manager;
    @Column(name = "u_comanager")
    private Boolean coManager;
    @Column(name = "u_login")
    private String login;
    @JsonIgnore
    @Column(name = "u_password")
    private String password;
    @Column(name = "u_viewmanager")
    private Boolean viewManager;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserTeam> userTeam;
    @Transient
    private List<Team> teams;
    @JsonIgnore
    @Column(name = "u_active")
    private Boolean isActive;

    public Boolean getViewManager() {
        return viewManager;
    }

    public void setViewManager(Boolean viewManager) {
        this.viewManager = viewManager;
    }

    public List<UserTeam> getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(List<UserTeam> userTeam) {
        this.userTeam = userTeam;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    public Boolean getCoManager() {
        return coManager;
    }

    public void setCoManager(Boolean coManager) {
        this.coManager = coManager;
    }

    public void addTeam(Team team) {
        if (teams != null) {
            teams.add(team);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                ", coManager=" + coManager +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
