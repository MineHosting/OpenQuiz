package eu.mhsl.openquiz.screen;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.sun.net.httpserver.HttpServer;
import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.io.HttpHandler;
import eu.mhsl.openquiz.out.Logger;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Implements the Providing-Menu for hosting
 *
 * okay, this Code is copyed from ScreenQuizList...
 * this is absolutly terrible and violates the DRY code-standart
 * but i have no time left for an 'good' and open implementation of ScreenQuizList.
 * Sorry :(
 * TODO: Change the ScreenQuizList to an open Return format and use it here, instead of copying code
 */
public class ScreenProvide implements Screen {
    public final TreeMap<Integer, String> items = new TreeMap<>();
    private final TreeMap<Integer, File> filemap = new TreeMap<>();

    public ScreenProvide() {
        items.put(1, "Zurück");

        File[] files = OpenQuiz.getFileHandler().getFiles();
        if(files != null) Arrays.sort(files, Comparator.comparingLong(File::lastModified));       //sort files by creation Date

        int c = 1;
        for(File file : Objects.requireNonNull(files)) {
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
        if (command == 1) {
            return new ScreenOpening();
        } else {
            if (!filemap.containsKey(command - 1)) return null;

            try {
                StringBuilder content = new StringBuilder();
                for (String line : Files.readAllLines(filemap.get(command - 1).toPath())) {
                    content.append(line).append("\n");
                }

                HttpServer server = HttpServer.create(new InetSocketAddress(OpenQuiz.getTerminal().number("auf welchem Port soll der Server gestartet werden?")), 0);
                server.createContext("/", new HttpHandler(content.toString()));
                server.setExecutor(null);
                server.start();
                Logger.info("Server is running!");
            } catch (Exception e) {
                Logger.error("Error while running Webserver or starting it: " + e.getMessage());
            }
        }

        return null;
    }
}
