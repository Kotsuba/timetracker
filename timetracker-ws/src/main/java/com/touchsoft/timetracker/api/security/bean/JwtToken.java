package com.touchsoft.timetracker.api.security.bean;

import java.io.Serializable;

public class JwtToken implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
