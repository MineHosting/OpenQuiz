package eu.mhsl.openquiz.out;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.screen.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Terminal {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Print a single free line
     */
    private static void free() {
        Terminal.free(1);
    }

    /**
     * Print an specific amount of free lines
     * @param lines amount
     */
    private static void free(int lines) {
        for(int i = 1; i <= lines; i++) {
            System.out.println();
        }
    }

    /**
     * Clear the Console-Screen (at least try it)
     */
    public void clearScreen() {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) Logger.warn("Be sure to activate ANSI in your Console to get proper ClearScreen on Windows OS");

        free(60); //only for fallback
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Prints an choice based on the given TreeMap and returns the key-value of selected
     * @param question Question to display
     * @param map map of possible Answers
     * @return key-value of selected map entry
     */
    public int choice(String question, TreeMap<Integer, String> map) {
        clearScreen();
        int answer = 0;
        int selected;

        System.out.println(Ansi.colorize(question, Attribute.BRIGHT_RED_TEXT()));

        for(int index : map.keySet().toArray(new Integer[map.size()])) {
            System.out.println(Ansi.colorize("" + index, Attribute.BOLD()) + " - " + map.get(index));
        }

        do {
            System.out.print(Ansi.colorize("Auswahl ", Attribute.CYAN_TEXT()) + Ansi.colorize(": ", Attribute.BOLD()));
            try {
                selected = Integer.parseInt(scanner.next());
            } catch(NumberFormatException e) {
                selected = -1;
            }
        } while(!map.containsKey(selected));

        return selected;
    }

    /**
     * Simple Terminal yes/no choice
     * @param question displayed question
     * @return yes equals true
     */
    public boolean bool(String question) {
        free();
        System.out.println(question);
        System.out.print("[y/N] : ");
        char selected = scanner.next().toLowerCase().toCharArray()[0];
        return selected == 'y';
    }

}
