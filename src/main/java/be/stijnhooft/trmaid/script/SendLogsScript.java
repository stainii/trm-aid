package be.stijnhooft.trmaid.script;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Slf4j
public class SendLogsScript implements Script {

    private static final Marker SMTP_TRIGGER = MarkerFactory.getMarker("SMTP_TRIGGER");

    @Override
    public void run(WebDriver driver) throws Exception {
        log.info(SMTP_TRIGGER, "All scripts have been run");
        Thread.sleep(1000L); // give Logback some time to send the mail
    }

}
