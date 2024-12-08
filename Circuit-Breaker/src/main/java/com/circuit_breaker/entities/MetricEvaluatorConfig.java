package com.circuit_breaker.entities;

import lombok.Getter;

@Getter
public class MetricEvaluatorConfig {
    private final int duration;
    private final int latencyThreshold;
    private final String responseType;
    private final int percentageThreshold;

    public MetricEvaluatorConfig(int duration, int latencyThreshold, String responseType, int percentageThreshold) {
        this.duration = duration;
        this.latencyThreshold = latencyThreshold;
        this.responseType = responseType;
        this.percentageThreshold = percentageThreshold;
    }
}
