package be.stijnhooft.trmaid.page;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class TimeRecordPage extends WebPage {

    public TimeRecordPage(WebDriver driver) {
        super(driver);
    }

    @Override
    Optional<WebElement> getAnElementFromThisPage() {
        try {
            WebElement form = driver.findElement(By.name("Form1"));
            if (form.getText()
                    .contains("Add time record")) {
                return Optional.of(form);
            }
        } catch(Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("Could not find form from timeRecordPage", e);
            } else {
                log.info("Could not find form from timeRecordPage");
            }
        }
        return Optional.empty();
    }

    @SneakyThrows
    public void selectProject(String projectNumber) {
        Select select = new Select(driver.findElement(By.name("TRMProjectSelector:TRMProjectSelector.cmbProject")));
        String optionToSelect = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .filter(text -> text.contains(projectNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not select project number " + projectNumber + ". Available options were " + select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList())));
        select.selectByVisibleText(optionToSelect);

        Thread.sleep(1000L);
    }

    @SneakyThrows
    public void selectTask(String taskNumber) {
        Select select = new Select(driver.findElement(By.name("TRMProjectSelector:TRMProjectSelector.cmbSubProject")));
        String optionToSelect = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .filter(text -> text.contains(taskNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not select task number " + taskNumber + ". Available options were " + select.getOptions().stream().map(WebElement::getText).collect(Collectors.toList())));
        select.selectByVisibleText(optionToSelect);

        Thread.sleep(1000L);
    }

    public void typeStartTimeAMField(String startTime) {
        driver.findElement(By.name("dgrClockPeriodParts:_ctl2:txtFromAM")).sendKeys(startTime);
    }

    public void typeEndTimeAMField(String endTime) {
        driver.findElement(By.name("dgrClockPeriodParts:_ctl2:txtTillAM")).sendKeys(endTime);
    }

    public void typeStartTimePMField(String startTime) {
        driver.findElement(By.name("dgrClockPeriodParts:_ctl2:txtFromPM")).sendKeys(startTime);
    }

    public void typeEndTimePMField(String endTime) {
        driver.findElement(By.name("dgrClockPeriodParts:_ctl2:txtTillPM")).sendKeys(endTime);
    }

    @SneakyThrows
    public void clickSaveButton() {
        driver.findElement(By.id("btnSave")).click();
        Thread.sleep(1000);
    }
}
