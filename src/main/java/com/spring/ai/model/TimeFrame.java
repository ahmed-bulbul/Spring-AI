package com.spring.ai.model;



import lombok.Data;


public class TimeFrame {

    private String period; // e.g., DAY, WEEK, MONTH
    private int value;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
