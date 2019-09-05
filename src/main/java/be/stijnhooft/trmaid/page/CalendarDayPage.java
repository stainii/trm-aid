package be.stijnhooft.trmaid.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

@Slf4j
public class CalendarDayPage extends WebPage {

    public CalendarDayPage(WebDriver driver) {
        super(driver);
    }

    @Override
    Optional<WebElement> getAnElementFromThisPage() {
        try {
            driver.switchTo().frame("punt_main");
        } catch (NoSuchFrameException e) {
            log.debug("Could not switch to frame punt_main. Possibly, we already are in the context of the frame. Continuing...", e);
        }

        return driver.findElements(By.className("header1"))
                .stream()
                .filter(element -> element.getText().contains("Calendar day"))
                .findFirst();
    }

    public boolean containsRecordForProject(String projectNumber) {
        return getTable().getText().contains(projectNumber);
    }

    private WebElement getTable() {
        return driver.findElement(By.className("tbl1"));
    }

    public boolean containsAbsence() {
        return !driver.findElements(By.className("bgAfw2")).isEmpty();
    }

    public void clickCalendarLink() {
        driver.findElement(By.linkText("calendar")).click();
    }

    public void clickAddTimeRecordLink() {
        driver.findElement(By.linkText("add time record")).click();
    }
}
