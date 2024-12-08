package com.circuit_breaker.entities;

import java.util.List;

public abstract class MetricEvaluator {
    protected final List<Metric<?>> metrics;
    protected final MetricEvaluatorConfig metricEvaluatorConfig;

    protected MetricEvaluator(List<Metric<?>> metrics, MetricEvaluatorConfig metricEvaluatorConfig) {
        this.metrics = metrics;
        this.metricEvaluatorConfig = metricEvaluatorConfig;
    }

    public abstract boolean evaluate();
}
