package pageFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;
    protected String baseUrl, nodeUrl;
    protected WebDriverWait wait;
    protected DesiredCapabilities capability;
    private String OS = null;

    public PageObject(RemoteWebDriver driver){
        capability = DesiredCapabilities.firefox();
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
        PageFactory.initElements(this.driver, this);
        setPlatformOS();
    }

    private void setPlatformOS(){
        if(OS == null){
            OS = System.getProperty("os.name");
        }
        if (OS.toLowerCase()=="windows"){
            capability.setPlatform(Platform.WINDOWS);
        } else if(OS.toLowerCase() == "linux"){
            capability.setPlatform(Platform.LINUX);
        } else if(OS.toLowerCase() == "mac"){
            capability.setPlatform(Platform.MAC);
        } else {
            capability.setPlatform(Platform.ANY);
        }
    }
    public void init() {
        this.driver.get(this.baseUrl);
    }
}
