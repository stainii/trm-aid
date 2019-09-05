package be.stijnhooft.trmaid;

import be.stijnhooft.trmaid.script.CompleteAllDaysScript;
import be.stijnhooft.trmaid.script.EightHoursEveryDayScript;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Slf4j
public class Main {

    private static Marker SMTP_TRIGGER = MarkerFactory.getMarker("SMTP_TRIGGER");


    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0 || args[0] == null || args[0].equals("")) {
            throw new IllegalStateException("Provide the parameter file location as first argument to this Java program");
        }
        String parameterFileLocation = args[0];

        runEightHoursEveryDayScript(parameterFileLocation);
        runCompleteAllDaysScript(parameterFileLocation);
        sendLogsViaEmail();
    }

    private static void sendLogsViaEmail() throws InterruptedException {
        log.info(SMTP_TRIGGER, "All scripts have been run");
        Thread.sleep(1000L); // give Logback some time to send the mail
    }

    private static void runCompleteAllDaysScript(String parameterFileLocation) {
        try {
            new CompleteAllDaysScript(parameterFileLocation)
                    .run();
        } catch (Exception e) {
            log.error("Something went wrong completing all days up until and including today.", e);
        }
    }

    private static void runEightHoursEveryDayScript(String parameterFileLocation) {
        try {
            new EightHoursEveryDayScript(parameterFileLocation)
                    .run();
        } catch (Exception e) {
            log.error("Something went wrong filling in eight hours every day up until and including today.", e);
        }
    }

}
