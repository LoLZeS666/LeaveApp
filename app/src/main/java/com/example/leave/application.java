package com.example.leave;

public class application {
    private String start, end, reason, type;

    public application(String start, String end, String reason, String type) {
        this.start = start;
        this.end = end;
        this.reason = reason;
        this.type = type;
    }

    public application() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
