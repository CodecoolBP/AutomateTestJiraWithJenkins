package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class EnvironmentManager {
    protected static WebDriver driver;
    protected static String driverPath = System.getenv("driverPath");
    protected static DesiredCapabilities capability;
    public static String nodeUrl = System.getenv("nodeUrl");


    public static void initChromeWebDriver() {

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();

        RunEnvironment.setWebDriver(driver);
    }

    public static void initChromeRemoteWebDriver(){
        capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        setDriver(capability,nodeUrl);
        RunEnvironment.setWebDriver(driver);
    }

    public static void initFireFoxWebDriver() {
        System.setProperty("webdriver.gecko.driver", driverPath );
        driver = new FirefoxDriver();

        RunEnvironment.setWebDriver(driver);
    }

    public static void initFireFoxRemoteWebDriver() {
        capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        setDriver(capability, nodeUrl);
        RunEnvironment.setWebDriver(driver);
    }
    protected static void setDriver(DesiredCapabilities capability,String nodeUrl){
        try {
            driver = new RemoteWebDriver(new URL(nodeUrl), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void shutDownDriver() {
        if(driver!=null) {
            System.out.println("Closing browser");
            RunEnvironment.getWebDriver().quit();
        }

    }

}
