package test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import pageFactory.Login;
import util.RunEnvironment;
import util.Utils;


public class TestLogin {

    Login login;
    WebDriver driver;

    @BeforeEach
    public void setup() {
        Utils.setup();
        driver = RunEnvironment.getWebDriver();
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
