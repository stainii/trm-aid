package be.stijnhooft.trmaid.script;

import be.stijnhooft.trmaid.page.LoginPage;
import be.stijnhooft.trmaid.helper.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.awaitility.Awaitility.await;

@Slf4j
public abstract class AbstractScript {

    protected WebDriver driver;
    protected Parameters parameters;
    protected LoginPage loginPage;

    public AbstractScript(String parameterFileLocation) {
        createDriver();
        parameters = new Parameters(parameterFileLocation);
        loginPage = new LoginPage(driver);
    }

    public void run() throws Exception {
        try {
            reset();
            login();
            runSpecificPartOfScript();
        } finally {
            driver.quit();
        }
    }

    protected abstract void runSpecificPartOfScript() throws Exception;

    protected void login() {
        log.info("Trying to log in");
        if (loginPage.isOnThisPage()) {
            loginPage.typeUsernameAndPassword(parameters.get(Parameters.USERNAME), parameters.get(Parameters.PASSWORD));
            loginPage.clickLoginButton();
            log.info("Logged in");
        } else {
            log.info("User is already logged in. Skipping login step");
        }
    }

    protected void reset() {
        driver.get(parameters.get(Parameters.URL));
    }

    private void createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
    }


}
