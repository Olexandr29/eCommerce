/*
package com.automation.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("-inprivate");
                return new EdgeDriver(edgeOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
} */
package com.automation.tests.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DriverFactory {

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                String chromeArgs = System.getProperty("chrome.args");
                if (chromeArgs != null) {
                    for (String arg : chromeArgs.split(" ")) {
                        chromeOptions.addArguments(arg);
                    }
                } else {
                    chromeOptions.addArguments("--incognito");
                }
   //                 chromeOptions.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());
                driver = new ChromeDriver(chromeOptions);
                break;


            //            case "brave":
//                WebDriverManager.chromedriver().setup();
//                ChromeOptions braveOptions = new ChromeOptions();
//                braveOptions.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
//                driver = new ChromeDriver(braveOptions);
//                break;

//            case "opera":
//                WebDriverManager.operadriver().setup();
//                driver = new OperaDriver();
//                break;

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