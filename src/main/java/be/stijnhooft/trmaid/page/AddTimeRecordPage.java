package be.stijnhooft.trmaid.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

@Slf4j
public class AddTimeRecordPage extends WebPage {

    public AddTimeRecordPage(WebDriver driver) {
        super(driver);
    }

    @Override
    Optional<WebElement> getAnElementFromThisPage() {
        try {
            return Optional.of(driver.findElement(By.id("calendar")));
        } catch (NoSuchElementException e) {
            if (log.isDebugEnabled()) {
                log.debug("Could not find calendar on calendar page.", e);
            } else {
                log.info("Could not find calendar on calendar page.");
            }
            return Optional.empty();
        }
    }


}
