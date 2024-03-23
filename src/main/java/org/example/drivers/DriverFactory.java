package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.PropertyManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    static PropertyManager propertyManager = new PropertyManager();

    static WebDriver getDriver() throws MalformedURLException{
        String browser = propertyManager.getProperty("BROWSER");
        if(browser == null){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        switch (browser){
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "SAFARI":
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            case "REMOTE":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                if("Y".equalsIgnoreCase(propertyManager.getProperty("HEADLESS"))){
                    chromeOptions.addArguments("--headless");
                }return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),chromeOptions);

            default:
                ChromeOptions chromeOption = new ChromeOptions();
                chromeOption.addArguments("--no-sandbox");
                chromeOption.addArguments("--disble-notifications");
                chromeOption.addArguments("start-maximized");
                if("Y".equalsIgnoreCase(propertyManager.getProperty("HEADLESS"))){
                    chromeOption.addArguments("--headless");}
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOption);

        }
    }
}
