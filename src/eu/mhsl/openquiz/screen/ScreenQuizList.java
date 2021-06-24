package eu.mhsl.openquiz.screen;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.question.QuestionSet;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Implements the Quiz-Selection menu
 */
public class ScreenQuizList implements Screen {
    public TreeMap<Integer, String> items = new TreeMap<>();
    private final TreeMap<Integer, File> filemap = new TreeMap<>();

    public ScreenQuizList() {
        items.put(1, "Zurück");

        File[] files = OpenQuiz.getFileHandler().getFiles();
        if(files != null) Arrays.sort(files, Comparator.comparingLong(File::lastModified));       //sort files by creation Date

        int c = 1;
        for(File file : files) {
            if(!file.isFile()) continue;                                        //skipping folders
            if(!file.getName().endsWith(".openquiz")) continue;                 //skip not correct fileendings
            if(!file.canRead()) continue;                                       //skip non-readable files

            this.filemap.put(c, file);

            String quizName = file.getName().split(".openquiz")[0];
            items.put(++c, Ansi.colorize(quizName, Attribute.BRIGHT_CYAN_TEXT()));
        }
    }
    public Screen display() {
        int command = OpenQuiz.getTerminal().choice("Wähle ein Quiz", items);
        switch (command) {
            case 1:
                return new ScreenOpening();
            default:
                if(!filemap.containsKey(command-1)) break;
                try {
                    return new ScreenQuizStart(new QuestionSet(filemap.get(command-1)));
                } catch(Exception e) {
                    Logger.error("Fehler beim einlesen oder Starten des Quizzes: " + e.getMessage());
                    OpenQuiz.getTerminal().pauseEnter("");
                    return new ScreenQuizList();
                }
        }

        return null;
    }

}
