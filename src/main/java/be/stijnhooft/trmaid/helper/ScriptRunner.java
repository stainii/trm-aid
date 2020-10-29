package be.stijnhooft.trmaid.helper;

import be.stijnhooft.trmaid.helper.Parameters;
import be.stijnhooft.trmaid.script.Script;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

import static org.awaitility.Awaitility.await;

@Slf4j
public class ScriptRunner {

    protected final WebDriver driver;
    protected final Parameters parameters;

    public ScriptRunner(Parameters parameters) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);

        this.parameters = parameters;
    }

    public void run(Script... scripts) {
        try {
            reset();
            Arrays.stream(scripts)
                    .forEach(script -> {
                        try {
                            script.run(driver);
                        } catch (Exception e) {
                            log.error("Something went wrong running script {}.", script.getClass().getName(), e);
                        }
                        reset();
                    });
        } finally {
            driver.quit();
        }
    }

    protected void reset() {
        driver.get(parameters.get(Parameters.URL));
    }


}
