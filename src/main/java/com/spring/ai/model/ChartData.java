package com.spring.ai.model;

import java.util.List;

public class ChartData {
    private List<String> labels; // Labels for the chart (e.g., categories)
    private List<Long> values;   // Values corresponding to the labels
    private String chartType;    // Type of chart (e.g., pie, bar, line)
    private String summary;      // Summary text for display

    // Getters and Setters
    public List<String> getLabels() {
        return labels;
    }

    public ChartData setLabels(List<String> labels) {
        this.labels = labels;
        return this;
    }

    public List<Long> getValues() {
        return values;
    }

    public ChartData setValues(List<Long> values) {
        this.values = values;
        return this;
    }

    public String getChartType() {
        return chartType;
    }

    public ChartData setChartType(String chartType) {
        this.chartType = chartType;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public ChartData setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    // Method to convert ChartData to a Chart.js configuration (as JSON)
    public String toChartJSConfig() {
        return """
            {
                type: "%s",
                data: {
                    labels: %s,
                    datasets: [{
                        data: %s,
                        backgroundColor: ["#FF6384", "#36A2EB", "#FFCE56"]
                    }]
                }
            }
            """.formatted(chartType, labels.toString(), values.toString());
    }
}
