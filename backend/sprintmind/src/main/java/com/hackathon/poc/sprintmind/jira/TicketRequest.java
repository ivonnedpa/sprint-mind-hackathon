package com.hackathon.poc.sprintmind.jira;

public class TicketRequest {

    private String summary;
    private String description;

    public TicketRequest() {
    }

    public TicketRequest(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}