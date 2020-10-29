package be.stijnhooft.trmaid.script;

import be.stijnhooft.trmaid.helper.DateUtil;
import be.stijnhooft.trmaid.helper.Parameters;
import be.stijnhooft.trmaid.page.CalendarPage;
import be.stijnhooft.trmaid.page.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class LoginScript implements Script {

    private final Parameters parameters;

    public LoginScript(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public void run(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);

        log.info("Trying to log in");
        try {
            await() .atMost(30, SECONDS)
                    .until(loginPage::isOnThisPage);
        } catch (Exception e) {
            log.info("Login page could not be opened. Maybe the user is already logged in? Skipping login step");
            return;
        }

        loginPage.fillInLoginDetails(parameters.get(Parameters.EMAIL), parameters.get(Parameters.PASSWORD));
        log.info("Logged in");
    }

}
