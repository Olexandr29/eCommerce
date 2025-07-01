package com.automation.tests.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");

                String firefoxArgs = System.getProperty("firefox.args");
                if (firefoxArgs != null) {
                    Arrays.stream(firefoxArgs.split(" ")).forEach(firefoxOptions::addArguments);
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                String chromeArgs = System.getProperty("chrome.args");
                if (chromeArgs != null) {
                    Arrays.stream(chromeArgs.split(" ")).forEach(chromeOptions::addArguments);
                }
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        generateAllureEnvironment(driver, browserName);

        AllureFilesGenerator.generateExecutorFile(driver, browserName);
        AllureFilesGenerator.generateCategoriesFile();

        return driver;
    }

    private static void generateAllureEnvironment(WebDriver driver, String browserName) {
        try {
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            String version = caps.getBrowserVersion();
            String platform = caps.getPlatformName().toString();

            File resultsDir = new File("target/allure-results");
            if (!resultsDir.exists()) {
                resultsDir.mkdirs();
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("target/allure-results/environment.properties"))) {
                writer.println("Browser=" + browserName);
                writer.println("Browser.Version=" + version);
                writer.println("Platform=" + platform);
            }

        } catch (Exception e) {
            System.err.println("❌ Could not write environment.properties: " + e.getMessage());
        }
    }
}