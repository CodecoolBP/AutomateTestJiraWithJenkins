package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {

    Login login;
    WebDriver driver;
    String nodeUrl;

    @BeforeEach
    public void setup() throws MalformedURLException {
        //Utils.setup();
        //driver = RunEnvironment.getWebDriver();

        nodeUrl = System.getenv("nodeUrl");
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        driver = new RemoteWebDriver(new URL(nodeUrl), capability);
        driver.manage().window().setSize(new Dimension(1024, 768));

        login = new Login(driver);
        driver.manage().window().maximize();
    }

    @DisplayName("Test login with wrong and empty details")
    @ParameterizedTest
    @CsvFileSource(resources = "/login/data-source-login.csv")
    void testLoginWithWrongDetails(String username, String password) {
        login.loginWithDashboard(username,password);
        Assertions.assertTrue(login.wrongLogin(), "Test login with wrong inputs");
        Assertions.assertFalse(login.isLoggedIn(), "Confirm you are not logged in");
    }

    @DisplayName("Successful login test")
    @Test
    public void testSuccessfulLoginAndLogout() {
        login.login();
        Assertions.assertTrue(login.isLoggedIn(), "Confirm the login was successful");
        login.logout();
        Assertions.assertFalse(login.isLoggedIn(), "Confirm the logout was successful");
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}
