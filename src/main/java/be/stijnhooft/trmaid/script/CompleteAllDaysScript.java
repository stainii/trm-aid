package be.stijnhooft.trmaid.script;

import be.stijnhooft.trmaid.page.CalendarPage;
import be.stijnhooft.trmaid.helper.DateUtil;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class CompleteAllDaysScript extends AbstractScript {

    private CalendarPage calendarPage;

    public CompleteAllDaysScript(String parameterFileLocation) {
        super(parameterFileLocation);
        DateUtil dateUtil = new DateUtil();
        calendarPage = new CalendarPage(driver, dateUtil);
        // endregion
    }

    protected void runSpecificPartOfScript() throws Exception {
        await() .atMost(30, SECONDS)
                .until(() -> calendarPage.isOnThisPage());

        log.info("Clicking \"complete all\".");
        calendarPage.clickCompleteAllLink();

        Thread.sleep(1000L);

        log.info("Clicking \"save changes\".");
        calendarPage.clickSaveChangesLink();

        log.info("All days completed until and including today.");
    }


    private void addMissingTimeRecordsForDay(String day) {
        log.info("Adding missing time records for day {}", day);
    }

}
