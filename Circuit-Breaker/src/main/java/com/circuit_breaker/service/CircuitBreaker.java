package com.circuit_breaker.service;

import com.circuit_breaker.entities.Metric;
import com.circuit_breaker.entities.impl.LatencyMetric;
import com.circuit_breaker.enums.MetricType;

import java.util.ArrayList;
import java.util.List;

public class CircuitBreaker {
    private final List<Metric<?>> metrics;

    private final List<Thread> evaulatorThreads;

    public CircuitBreaker() {
        evaulatorThreads = new ArrayList<>();
        metrics = new ArrayList<>();
    }

    public String getResponse(String des, String payload) {
        // Call to des and then record the latency.
        metrics.add(new LatencyMetric(System.currentTimeMillis(), MetricType.LATENCY, 100L));
        return "";
    }
}
