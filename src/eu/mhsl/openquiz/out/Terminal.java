package eu.mhsl.openquiz.out;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.question.Question;
import eu.mhsl.openquiz.screen.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;

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
     * Helper for choice
     * @param question
     * @param map
     * @return
     */
    public int choice(String question, TreeMap<Integer, String> map) {
        return this.choice(question, map, false);
    }

    /**
     * Prints an choice based on the given TreeMap and returns the key-value of selected
     * @param question Question to display
     * @param map map of possible Answers
     * @param noClear on true disables previous clearscreen
     * @return key-value of selected map entry
     */
    public int choice(String question, TreeMap<Integer, String> map, boolean noClear) {
        if(!noClear) clearScreen();
        int answer = 0;
        int selected;
        free();
        System.out.println(Ansi.colorize(question, Attribute.BRIGHT_RED_TEXT()));
        free();
        for(int index : map.keySet().toArray(new Integer[map.size()])) {
            System.out.println(Ansi.colorize("" + index, Attribute.BOLD()) + " - " + map.get(index));
        }
        free();
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

    /**
     * Asks user for an Number-input
     * @param question displayed question
     * @param prefered an standart-input if nothing is providet
     * @return the given number
     */
    public int number(String question, int prefered) {
        scanner.nextLine();
        free();
        System.out.println(question);
        System.out.print("[" + prefered + "]: ");
        String input = scanner.nextLine();
        if(input.equals("")) {
            return prefered;
        }
        return Integer.parseInt(input);
    }

    /**
     * Asks for the Answer of an Quiz-Obj.
     * @param quest the Question
     * @param index the Question number in the full quiz
     * @param player the Player who should answer
     * @return true if the Question was answered correctly
     */
    public boolean quizQuestion(Question quest, int index, int player) {
        clearScreen();
        TreeMap<Integer, String> items = new TreeMap<>();
        System.out.println(Ansi.colorize("Spieler: " + player + " Frage: " + index, Attribute.BOLD()));

        int count = 0;
        for(String answer : quest.getAnswers()) {
            count++;
            items.put(count, answer);
        }
        int answer = this.choice(quest.getQuestion(), items, true);

        return answer != quest.getSolution();
    }

}
