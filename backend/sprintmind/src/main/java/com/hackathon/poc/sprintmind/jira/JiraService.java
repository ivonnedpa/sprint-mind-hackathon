package com.hackathon.poc.sprintmind.jira;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiraService {

    private static final String JIRA_URL = "https://ivonnedpa.atlassian.net";
    private static final String EMAIL = "ivonnedpa@gmail.com";
    private static final String PROJECT_KEY = "HAC";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpClient client = HttpClient.newBuilder()
            .sslContext(trustAllSslContext())
            .build();

    public String createIssue(String summary, String description) {

        String endpoint = JIRA_URL + "/rest/api/3/issue";
        String auth = EMAIL + ":" + "token";

        String encodedAuth = Base64.getEncoder()
                .encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        try {

            // Project
            Map<String, Object> project = new HashMap<>();
            project.put("key", PROJECT_KEY);

            // Issue Type
            Map<String, Object> issueType = new HashMap<>();
            issueType.put("name", "Task");

            // ADF text node
            Map<String, Object> textNode = Map.of(
                    "type", "text",
                    "text", description
            );

            // ADF paragraph node
            Map<String, Object> paragraphNode = Map.of(
                    "type", "paragraph",
                    "content", List.of(textNode)
            );

            // ADF document
            Map<String, Object> descriptionNode = Map.of(
                    "type", "doc",
                    "version", 1,
                    "content", List.of(paragraphNode)
            );

            // Fields
            Map<String, Object> fields = new HashMap<>();
            fields.put("project", project);
            fields.put("summary", summary);
            fields.put("description", descriptionNode);
            fields.put("issuetype", issueType);

            // Full payload
            Map<String, Object> payload = Map.of("fields", fields);

            String json = objectMapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic " + encodedAuth)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return response.body();
            }

            throw new RuntimeException(
                    "Jira API failed: " + response.statusCode() + " - " + response.body()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Jira issue", e);
        }
    }

    // ⚠️ TEMPORARY: Trust-all SSL (fixes PKIX issue but not production-safe)
    private SSLContext trustAllSslContext() {
        try {
            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAll, new SecureRandom());
            return sslContext;

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize trust-all SSL context", e);
        }
    }
}