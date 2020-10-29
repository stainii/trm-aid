package be.stijnhooft.trmaid;

import be.stijnhooft.trmaid.helper.Parameters;
import be.stijnhooft.trmaid.helper.ScriptRunner;
import be.stijnhooft.trmaid.script.CompleteAllDaysScript;
import be.stijnhooft.trmaid.script.FillInHoursScript;
import be.stijnhooft.trmaid.script.LoginScript;
import be.stijnhooft.trmaid.script.SendLogsScript;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Parameters parameters = getParameters(args);
        runScripts(parameters);
    }


    private static Parameters getParameters(String[] args) {
        if (args.length == 0 || args[0] == null || args[0].equals("")) {
            throw new IllegalStateException("Provide the parameter file location as first argument to this program");
        }
        String parameterFileLocation = args[0];
        return new Parameters(parameterFileLocation);
    }

    private static void runScripts(Parameters parameters) {
        ScriptRunner scriptRunner = new ScriptRunner(parameters);
        scriptRunner.run(
                new LoginScript(parameters),
                new FillInHoursScript(parameters),
                new CompleteAllDaysScript(),
                new SendLogsScript());
    }

}
