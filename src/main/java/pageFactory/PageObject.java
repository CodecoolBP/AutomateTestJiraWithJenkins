package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;
    protected String baseUrl = "https://jira.codecool.codecanvas.hu";
    protected WebDriverWait wait;


    public PageObject(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
        PageFactory.initElements(this.driver, this);
    }


    public void init() {
        this.driver.get(this.baseUrl);
    }
}
