package com.hackathon.poc.sprintmind.controller;

import com.hackathon.poc.sprintmind.jira.JiraService;
import com.hackathon.poc.sprintmind.jira.TicketRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final JiraService jiraService;

    public TicketController(
            JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(
            @RequestBody TicketRequest request) {

        String result =
                jiraService.createIssue(
                        request.getSummary(),
                        request.getDescription());

        return ResponseEntity.ok(result);
    }
}