package com.opencart.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverRepository {

    public static final ThreadLocal<WebDriver> DRIVERS = new ThreadLocal<>();
    private static WebDriver webDriver;
    private static String browserName;

    private DriverRepository() {
    }

    public static void downloadWebDriver(String browserName) {
        DriverRepository.browserName = browserName;
        switch (DriverRepository.browserName) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
            default:
                System.err.println("Wrong browser name");
                break;
        }
    }

    public static void instanceWebBrowser() {
        switch (DriverRepository.browserName) {
            case "Chrome":
                webDriver = new ChromeDriver();
                break;
            case "Firefox":
                webDriver = new FirefoxDriver();
                break;
            case "Edge":
                webDriver = new EdgeDriver();
                break;
        }
        DRIVERS.set(webDriver);
    }

    public static void closeBrowser() {
        webDriver.quit();
    }
}
