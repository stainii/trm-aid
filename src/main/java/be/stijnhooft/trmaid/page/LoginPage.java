package be.stijnhooft.trmaid.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

@Slf4j
public class LoginPage extends WebPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    Optional<WebElement> getAnElementFromThisPage() {
        try {
            return Optional.of(getUsernameField());
        } catch (NoSuchElementException e) {
            if (log.isDebugEnabled()) {
                log.debug("Could not find username field on login page.", e);
            } else {
                log.info("Could not find username field on login page.");
            }

            return Optional.empty();
        }
    }

    public void typeUsernameAndPassword(String username, String password) {
        getUsernameField().sendKeys(username);
        getPasswordField().sendKeys(password);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }


    private WebElement getUsernameField() {
        return driver.findElement(By.name("login"));
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.name("passwd"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.id("Log_On"));
    }
}
