package be.stijnhooft.trmaid.script;

import be.stijnhooft.trmaid.page.CalendarDayPage;
import be.stijnhooft.trmaid.helper.DateUtil;
import be.stijnhooft.trmaid.helper.Parameters;
import be.stijnhooft.trmaid.page.CalendarPage;
import be.stijnhooft.trmaid.page.TimeRecordPage;
import lombok.extern.slf4j.Slf4j;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
public class EightHoursEveryDayScript extends AbstractScript {
    private CalendarPage calendarPage;
    private CalendarDayPage calendarDayPage;
    private TimeRecordPage timeRecordPage;

    public EightHoursEveryDayScript(String parameterFileLocation) {
        super(parameterFileLocation);
        DateUtil dateUtil = new DateUtil();
        calendarPage = new CalendarPage(driver, dateUtil);
        calendarDayPage = new CalendarDayPage(driver);
        timeRecordPage = new TimeRecordPage(driver);
    }


    protected void runSpecificPartOfScript() {
        log.info("Determining which days need to be filled in and completed.");

        await() .atMost(30, SECONDS)
                .until(() -> calendarPage.isOnThisPage());

        calendarPage.getWeekDaysUntilAndIncludingToday()
                .forEach(day ->  {

                    try {
                        addMissingTimeRecordsForDay(day);
                    } catch (Exception e) {
                        log.error("Something went wrong when filling in day " + day, e);
                        reset();
                    }
                });
    }

    private void addMissingTimeRecordsForDay(String dayNumber) {
        log.info("Checking for missing time records for day {}", dayNumber);

        await().atMost(30, SECONDS)
                .until(() -> calendarPage.isOnThisPage());

        calendarPage.clickOnDay(dayNumber);

        await().atMost(30, SECONDS)
                .until(() -> calendarDayPage.isOnThisPage());

        if (calendarDayPage.containsRecordForProject(parameters.get(Parameters.PROJECT_NUMBER)) ||
                calendarDayPage.containsAbsence()) {
            log.info("No missing time records for day {}", dayNumber);
        } else {
            calendarDayPage.clickAddTimeRecordLink();

            await()
                .atMost(30, SECONDS)
                .until(() -> timeRecordPage.isOnThisPage());

            timeRecordPage.selectProject(parameters.get(Parameters.PROJECT_NUMBER));
            timeRecordPage.selectTask(parameters.get(Parameters.PROJECT_TASK_NUMBER));
            timeRecordPage.typeStartTimeAMField("09:00");
            timeRecordPage.typeEndTimeAMField("12:00");
            timeRecordPage.typeStartTimePMField("12:30");
            timeRecordPage.typeEndTimePMField("17:30");
            timeRecordPage.clickSaveButton();
            log.info("Added time record.");

            // wait until popup is closed
            calendarDayPage.isOnThisPage();
        }

        // going back
        calendarDayPage.clickCalendarLink();
        await().atMost(30, SECONDS)
                .until(() -> calendarPage.isOnThisPage());
    }



}
