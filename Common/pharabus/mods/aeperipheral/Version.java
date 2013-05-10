package pharabus.mods.aeperipheral;

import java.util.Properties;

public class Version {
    private static String major;
    private static String minor;
    private static String rev;
    private static String build;

    static void init(Properties properties) {

        major = "1";
        minor = "0";
        rev = "0";
        build = "0";

    }

    public static String fullVersionString() {
        return String.format("%s.%s.%s build %s", major, minor, rev, build);
    }
}
