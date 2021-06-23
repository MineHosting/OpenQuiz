package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.OpenQuiz;

import java.util.TreeMap;

/**
 * Implements the Import-Option menu
 */
public class ScreenImport implements Screen {
    public TreeMap<Integer, String> items = new TreeMap<>();
    public ScreenImport() {
        items.put(1, "Zurück");
        items.put(2, "OpenQuiz Server");
        items.put(3, "HTTP");
        items.put(4, "File");
    }
    public Screen display() {
        int command = OpenQuiz.getTerminal().choice("Importieren von", items);
        switch (command) {
            case 2:
                OpenQuiz.getFileHandler().importServer();
                break;
            case 3:
                OpenQuiz.getFileHandler().importHTTP();
                break;
            case 4:
                OpenQuiz.getFileHandler().importFile();
                break;
            default:
                return null;
        }

        return new ScreenOpening();
    }
}
