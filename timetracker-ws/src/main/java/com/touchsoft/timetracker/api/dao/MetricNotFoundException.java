package com.touchsoft.timetracker.api.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Metric not found")
public class MetricNotFoundException extends RuntimeException {
    public MetricNotFoundException() {
        super();
    }

    public MetricNotFoundException(String message) {
        super(message);
    }
}
