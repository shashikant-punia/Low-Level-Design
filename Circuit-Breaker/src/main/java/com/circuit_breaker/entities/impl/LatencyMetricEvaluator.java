package com.circuit_breaker.entities.impl;

import com.circuit_breaker.entities.Metric;
import com.circuit_breaker.entities.MetricEvaluator;
import com.circuit_breaker.entities.MetricEvaluatorConfig;
import com.circuit_breaker.enums.MetricType;
import com.circuit_breaker.enums.State;

import java.util.List;

public class LatencyMetricEvaluator extends MetricEvaluator implements Runnable {
    private State state;

    private volatile boolean isRunning = false;


    public LatencyMetricEvaluator(State state, MetricEvaluatorConfig metricEvaluatorConfig, List<Metric<?>> metrics) {
        super(metrics, metricEvaluatorConfig);
        this.state = state;
    }

    @Override
    public boolean evaluate() {
        int failures = 0;
        int total = 0;

        long lastTimeStamp = System.currentTimeMillis() - metricEvaluatorConfig.getDuration();

        for (int index = metrics.size() - 1; index >= 0; index--) {
            if (metrics.get(index).getMetricType() == MetricType.ERROR)
                continue;
            if (metrics.get(index).getTimestamp() < lastTimeStamp) {
                break;
            }
            if ((Long)metrics.get(index).getMetricValue() >= metricEvaluatorConfig.getLatencyThreshold())
                failures++;
            total++;
        }

        double percentageFailures = (failures / (double)total) * 100;

        return percentageFailures >= metricEvaluatorConfig.getPercentageThreshold();
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning && !Thread.currentThread().isInterrupted()) {
            if (evaluate()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                this.state = State.OPEN;
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
