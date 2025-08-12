package com.testpilotx.core.model;

import java.util.UUID;

public class TestCase {
    private String id;
    private String name;
    private String description;
    private String script;

    public TestCase() {
        this.id = UUID.randomUUID().toString(); // Auto-generate ID by default
    }

    public TestCase(String name, String description, String script) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.script = script;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) { // Optional if we ever need to set manually
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
