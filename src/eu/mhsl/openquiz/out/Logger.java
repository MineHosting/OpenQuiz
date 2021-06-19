package eu.mhsl.openquiz.out;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

/**
 * This is not an File-based Logger!
 * It only sends Prefixed text to Console.
 */
public class Logger {
    public static void warn(String text) {
        System.out.println("[" + Ansi.colorize("WARNING", Attribute.YELLOW_TEXT()) + "] " + text);
    }
    public static void error(String text) {
        System.out.println("[" + Ansi.colorize("ERROR", Attribute.RED_TEXT()) + "] " + text);
    }
    public static void info(String text) {
        System.out.println("[" + Ansi.colorize("INFO", Attribute.WHITE_TEXT()) + "] " + text);
    }
}
