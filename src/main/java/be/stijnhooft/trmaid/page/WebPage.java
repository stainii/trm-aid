package be.stijnhooft.trmaid.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

@Slf4j
public abstract class WebPage {

    protected final WebDriver driver;

    public WebPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnThisPage() {
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            // first, select a window
            try {
                driver.switchTo().window(windowHandle);
            } catch (NoSuchWindowException e) {
                log.debug("Could not switch to window.", e);
            }

            try {
                if (getAnElementFromThisPage().isPresent()) {
                    log.info("We're on page {}.", this.getClass().getCanonicalName());
                    return true;
                }
            } catch (Exception e) {
                log.debug("We do not seem to be on the {}. Checking other frames, if there are any.", this.getClass().getName(), e);
            }

            log.info("We're not on page {} in the current window. If there are any other windows, switching to the next one.", this.getClass().getName());
        }

        // element not found, we're not on the page
        log.info("We're not on page {}.", this.getClass().getName());
        return false;
    }

    abstract Optional<WebElement> getAnElementFromThisPage();

}
