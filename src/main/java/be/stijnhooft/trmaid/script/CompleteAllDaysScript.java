package be.stijnhooft.trmaid.script;

import be.stijnhooft.trmaid.helper.Parameters;
import be.stijnhooft.trmaid.page.CalendarPage;
import be.stijnhooft.trmaid.helper.DateUtil;
import be.stijnhooft.trmaid.page.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class CompleteAllDaysScript implements Script {


    @Override
    public void run(WebDriver driver) throws Exception {
        DateUtil dateUtil = new DateUtil();
        CalendarPage calendarPage = new CalendarPage(driver, dateUtil);

        await() .atMost(30, SECONDS)
                .until(calendarPage::isOnThisPage);

        log.info("Clicking \"complete all\".");
        calendarPage.clickCompleteAllLink();

        Thread.sleep(1000L);

        log.info("Clicking \"save changes\".");
        calendarPage.clickSaveChangesLink();

        log.info("All days completed until and including today.");
    }

}
