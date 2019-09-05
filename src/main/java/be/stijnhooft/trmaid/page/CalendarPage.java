package be.stijnhooft.trmaid.page;

import be.stijnhooft.trmaid.helper.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class CalendarPage extends WebPage {

    private final DateUtil dateUtil;

    public CalendarPage(WebDriver driver, DateUtil dateUtil) {
        super(driver);
        this.dateUtil = dateUtil;
    }

    @Override
    Optional<WebElement> getAnElementFromThisPage() {
        try {
            driver.switchTo().frame("punt_main");
        } catch (NoSuchFrameException e) {
            log.debug("Could not switch to frame punt_main. Possibly, we already are in the context of the frame. Continuing...", e);
        }

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


    public Stream<String> getWeekDaysUntilAndIncludingToday() {
        return dateUtil.getWeekDaysOfMonthUntilAndIncludingToday()
                .map(LocalDate::getDayOfMonth)
                .map(Object::toString);
    }

    public void clickOnDay(String day) {
        driver.findElement(By.linkText(day)).click();
    }

    public void clickCompleteAllLink() {
        driver.findElement(By.linkText("complete all"))
                .click();
    }

    public void clickSaveChangesLink() {
        driver.findElement(By.linkText("save changes"))
                .click();
    }
}
