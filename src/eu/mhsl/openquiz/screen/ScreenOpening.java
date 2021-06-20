package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.Main;

import java.util.TreeMap;

/**
 * Implements the Main or Home-Screen
 */
public class ScreenOpening implements Screen {
    public TreeMap<Integer, String> items = new TreeMap<>();
    public ScreenOpening() {
        items.put(1, "Spielen");
        items.put(2, "Erstellen");
        items.put(3, "Importieren");
        items.put(4, "Hosten");
        items.put(5, "Beenden");
    }

    public Screen display() {
        int command = Main.getTerminal().choice("OpenQuiz", items);
        switch (command) {
            case 1:
                return new ScreenQuizList();
            case 3:
                return new ScreenImport();
            default:
                return null;
        }
    }

}
