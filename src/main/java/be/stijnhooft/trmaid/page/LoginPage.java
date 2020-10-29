package be.stijnhooft.trmaid.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.awaitility.Awaitility.await;

@Slf4j
public class LoginPage extends WebPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    Optional<WebElement> getAnElementFromThisPage() {
        var emailField = getEmailField();
        if (emailField.isEmpty()) {
            log.info("Could not find username field on login page.");
        }
        return emailField;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void fillInLoginDetails(String email, String password) {
        sleep1Second();

        await().atMost(1, TimeUnit.MINUTES)
                .until(() -> getEmailField().isPresent());
        getEmailField().get().sendKeys(email);
        getSubmitEmailButton().get().click();

        sleep1Second();

        await().atMost(1, TimeUnit.MINUTES)
                .until(() -> getPasswordField().isPresent());
        getPasswordField().get().sendKeys(password);
        getSubmitPasswordButton().get().click();

        sleep1Second();

        await().atMost(10, TimeUnit.MINUTES)
                .until(() -> getYesButton().isPresent());
        getYesButton().get().click();
    }

    private void sleep1Second() {
        try {
            sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<WebElement> getEmailField() {
        return driver.findElements(By.cssSelector("input[type=email]"))
                .stream()
                .findFirst();
    }

    private Optional<WebElement> getPasswordField() {
        return driver.findElements(By.id("passwordInput"))
                .stream()
                .findFirst();
    }

    private Optional<WebElement> getSubmitEmailButton() {
        return driver.findElements(By.cssSelector("input[type=submit]"))
                .stream()
                .findFirst();
    }

    private Optional<WebElement> getSubmitPasswordButton() {
        return driver.findElements(By.id("submitButton"))
                .stream()
                .findFirst();
    }

    private Optional<WebElement> getYesButton() {
        return driver.findElements(By.className("primary"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst();
    }
}
