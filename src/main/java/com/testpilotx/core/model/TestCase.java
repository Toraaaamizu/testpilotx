package com.testpilotx.core.model;

import java.util.UUID;

public class TestCase {
    private String id;
    private String name;
    private String description;
    private String script;

    public TestCase() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getScript() { return script; }
    public void setScript(String script) { this.script = script; }
}
