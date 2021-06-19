package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.Main;

import java.util.TreeMap;

public class ScreenImport implements Screen {
    public TreeMap<Integer, String> items = new TreeMap<>();
    public ScreenImport() {
        items.put(1, "Zurück");
        items.put(2, "OpenQuiz Server");
        items.put(3, "HTTP");
        items.put(4, "File");
    }
    public Screen display() {
        int command = Main.getTerminal().choice("Importieren von", items);
        switch (command) {
            case 1:
                return new ScreenOpening();
            default:
                return null;
        }
    }
}
