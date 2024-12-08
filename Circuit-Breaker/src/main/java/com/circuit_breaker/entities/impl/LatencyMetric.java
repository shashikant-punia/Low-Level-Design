package com.circuit_breaker.entities.impl;

import com.circuit_breaker.entities.Metric;
import com.circuit_breaker.enums.MetricType;

public class LatencyMetric extends Metric<Long> {
    private final Long latencyRecorded;

    public LatencyMetric(Long timestamp, MetricType metricType, Long latencyRecorded) {
        super(timestamp, metricType);
        this.latencyRecorded = latencyRecorded;
    }

    @Override
    public Long getMetricValue() {
        return latencyRecorded;
    }
}
