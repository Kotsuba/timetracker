package com.touchsoft.timetracker.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "projects", catalog = "timetracker")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_id")
    private Long id;
    @JsonIgnore
    @Column(name = "p_timestamp")
    private Timestamp timestamp;
    @Column(name = "p_name")
    private String name;
    @JsonIgnore
    @Column(name = "p_manager_id")
    private Long managerId;
    @JsonIgnore
    @Column(name = "p_status")
    private Boolean status;
    @OneToMany(mappedBy = "team")
    private List<UserTeam> userTeam;

    public List<UserTeam> getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(List<UserTeam> userTeam) {
        this.userTeam = userTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
                ", status=" + status +
                '}' + "\n";
    }
}
