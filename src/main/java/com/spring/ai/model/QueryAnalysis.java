package com.spring.ai.model;


import lombok.Data;


public class QueryAnalysis {
    private String operation; // e.g., COUNT, SUM, AVG
    private String entity;    // e.g., users, roles
    private TimeFrame timeFrame;
    private String chartType; // e.g., pie, bar, line

    public QueryAnalysis() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }
}
