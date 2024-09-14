package org.user;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void getUserActivity(String userName) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.github.com/users/" + userName + "/events"))
                .build();

        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response.body());

            if (root.isArray()) {
                for (JsonNode event : root) {

                    String id = event.has("id") ? event.get("id").asText() : "N/A";
                    String type = event.has("type") ? event.get("type").asText() : "N/A";

                    String actorLogin = event.has("actor") && event.get("actor").has("login")
                            ? event.get("actor").get("login").asText()
                            : "N/A";

                    String repoName = event.has("repo") && event.get("repo").has("name")
                            ? event.get("repo").get("name").asText()
                            : "N/A";

                    String commitMessage = "No commit message available";
                    if (event.has("payload") && event.get("payload").has("commits")) {
                        JsonNode commits = event.get("payload").get("commits");
                        if (commits.isArray() && (commits.size() > 0)) {
                            commitMessage = commits.get(0).has("message")
                                    ? commits.get(0).get("message").asText()
                                    : "No commit message";
                        }
                    }

                    System.out.println("Event ID: " + id);
                    System.out.println("Event Type: " + type);
                    System.out.println("Actor Login: " + actorLogin);
                    System.out.println("Repository Name: " + repoName);
                    System.out.println("Commit Message: " + commitMessage);
                    System.out.println("----------------------------");
                }
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        getUserActivity(userName);
    }
}