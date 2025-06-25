package com.automation.tests.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureFilesGenerator {

    public static void generateExecutorFile(WebDriver driver, String browserName) {
        try {
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            String version = caps.getBrowserVersion();
            String platform = caps.getPlatformName().toString();
            String tag = System.getProperty("groups", "unknown");

            // Форматування дати для зручного buildName
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));

            String buildName = browserName + " ver. " + version + "  - " + tag + " - " + timeStamp + " , " + platform;

            String content = "{\n" +
                    "  \"name\": \"Local Run\",\n" +
                    "  \"type\": \"local\",\n" +
                    "  \"buildName\": \"" + buildName + "\",\n" +
                    "  \"reportName\": \"Allure Report\"\n" +
                    "}";

            Files.write(Paths.get("target/allure-results/executor.json"),
                    content.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.err.println("❌ Could not write executor.json: " + e.getMessage());
        }
    }

    public static void generateCategoriesFile() {
        String json = """
            [
              {
                "name": "Assertion Failed",
                "matchedStatuses": ["failed"],
                "messageRegex": ".*AssertionError.*"
              },
              {
                "name": "Element Not Found",
                "matchedStatuses": ["broken"],
                "messageRegex": ".*NoSuchElementException.*"
              },
              {
                "name": "Timeouts",
                "matchedStatuses": ["broken"],
                "messageRegex": ".*TimeoutException.*"
              }
            ]
            """;

        writeToFile("target/allure-results/categories.json", json);
    }

    private static void writeToFile(String path, String content) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
            }
        } catch (IOException e) {
            System.err.println("❌ Could not write Allure config file: " + e.getMessage());
        }
    }
}