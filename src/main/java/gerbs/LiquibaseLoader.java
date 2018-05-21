package gerbs;

import liquibase.integration.commandline.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main Class to execute liquibase database script
 * <p>
 * need to provide command line args
 * <p>
 * --url=jdbc:mariadb://localhost:3306/TURBINE
 * --username=turbineuser
 * --password=turbinepwd
 * <p>
 * Created by markus.plattner.xax on 20.09.16.
 */
public class LiquibaseLoader {

    private static final String CMD_PARAM = "--cmd";
    private static final String DRIVER_PARAM = "--driver";
    private static final String FILE_PARAM = "--changeLogFile";

    public static void main(String[] args) {
        try {
            System.out.println("configuring datalake database");

            if (args.length < 3) {
                System.out.println("missing arguments");
                System.exit(-1);
            }
            String cmd = "update";
            List<String> toolArgs = new ArrayList<String>(); Collections.addAll(toolArgs, args);
            List<String> toRemove = new ArrayList<String>();
            boolean hasDriver = false;
            boolean hasFile = false;
            for (String arg : toolArgs) {
                if (arg.startsWith(CMD_PARAM) && arg.indexOf("=") > 0) {
                    cmd = arg.substring(arg.indexOf("=") + 1);
                    toRemove.add(arg);
                }
                else if (arg.startsWith(DRIVER_PARAM)) {
                    hasDriver = true;
                }
                else if (arg.startsWith(FILE_PARAM)) {
                    hasFile = true;
                }

            }
            toolArgs.removeAll(toRemove);
            if (!hasDriver) {
                toolArgs.add("--driver=com.vertica.jdbc.Driver");
            }
            if (!hasFile) {
                toolArgs.add("--changeLogFile=/liquibase/vertica/changelogs/changelog-master.xml");
            }
            System.out.println("running with command " + cmd);
            toolArgs.add(cmd);
            String[] newArgs = new String[toolArgs.size()];
            for (int i = 0; i < toolArgs.size(); i++) {
                newArgs[i] = toolArgs.get(i);
            }
            Main.main(newArgs);

            System.out.println("xrep database configured");
        }
        catch (Throwable ex) {
            System.err.println("xrep database configuration failed");
            ex.printStackTrace(System.err);
            System.exit(-1);
        }
    }

}
