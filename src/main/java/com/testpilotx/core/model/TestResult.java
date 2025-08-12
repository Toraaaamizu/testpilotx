package com.testpilotx.core.model;

import java.time.LocalDateTime;

public class TestResult {
    private String testId;
    private boolean passed;
    private String output;
    private LocalDateTime executedAt;

    public TestResult(String testId, boolean passed, String output) {
        this.testId = testId;
        this.passed = passed;
        this.output = output;
        this.executedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getTestId() { return testId; }
    public void setTestId(String testId) { this.testId = testId; }
    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }
    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }
    public LocalDateTime getExecutedAt() { return executedAt; }
    public void setExecutedAt(LocalDateTime executedAt) { this.executedAt = executedAt; }
}
