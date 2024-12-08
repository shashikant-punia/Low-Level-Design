package com.circuit_breaker.entities;

import com.circuit_breaker.enums.MetricType;
import lombok.Getter;

@Getter
public abstract class  Metric<T> {
    private final Long timestamp;
    private final MetricType metricType;

    protected Metric(Long timestamp, MetricType metricType) {
        this.timestamp = timestamp;
        this.metricType = metricType;
    }

    public abstract T getMetricValue();
}
