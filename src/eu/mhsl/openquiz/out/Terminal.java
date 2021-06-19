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
    private Scanner scanner = new Scanner(System.in);

    private static void free() {
        Terminal.free(1);
    }
    private static void free(int lines) {
        for(int i = 1; i <= lines; i++) {
            System.out.println();
        }
    }

    public void clearScreen() {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) Logger.warn("Be sure to activate ANSI in your Console to get proper ClearScreen on Windows OS");

        free(60); //only for fallback
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printScreen(Screen s) {
        s.display();
    }

    public int choice(TreeMap<Integer, String> items) {
        return this.choice("", items);
    }
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

    public void anyKey() {
        System.out.println("Drücke eine belibige Taste...");
        try {
            System.in.read();
        }
        catch(Exception e) {} //hmm
    }

}
