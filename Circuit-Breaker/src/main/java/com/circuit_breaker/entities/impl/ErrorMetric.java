package com.circuit_breaker.entities.impl;

import com.circuit_breaker.entities.Metric;
import com.circuit_breaker.enums.MetricType;

public class ErrorMetric extends Metric<String> {
    private final String responseRecorded;

    protected ErrorMetric(Long timestamp, MetricType metricType, String responseRecorded) {
        super(timestamp, metricType);
        this.responseRecorded = responseRecorded;
    }

    @Override
    public String getMetricValue() {
        return responseRecorded;
    }
}
