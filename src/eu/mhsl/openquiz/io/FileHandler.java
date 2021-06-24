package eu.mhsl.openquiz.io;

import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.out.Terminal;
import eu.mhsl.openquiz.question.Question;
import eu.mhsl.openquiz.question.QuestionSet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Filehandler is an basic IO class which handles files
 */
public class FileHandler {
    private final File storage;
    public FileHandler() {
        this.storage = new File(System.getProperty("user.dir") + File.separator + "data");
        if(storage.mkdirs()) Logger.warn("Created 'data' directory");
    }

    public File[] getFiles() {
        return storage.listFiles();
    }

    /**
     * Imports openquizfile via HTTP-Link
     */
    public void importHTTP() {
        Terminal t = OpenQuiz.getTerminal();
        String url = t.text("Link: ");
        String name = t.text("Speichern als: ");
        try {

            /**
             * Download, move and name target file
             */
            Logger.info("Herunterladen...");
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(this.storage.getAbsolutePath() + File.separator + name + ".openquiz"), StandardCopyOption.REPLACE_EXISTING);
            Logger.info("Download fertig!");

        } catch(MalformedURLException e) {
            Logger.error("Die angegebene URL ist nicht valide!");
        } catch(IOException e) {
            Logger.error("Fehler beim Zugriff auf das Dateisystem");
        } finally {
            t.pauseEnter("");
        }
    }

    /**
     * Copys openquiz file to the internal directory
     */
    public void importFile() {
        Terminal t = OpenQuiz.getTerminal();
        String path = t.text("File: ");
        String name = t.text("Speichern als: ");
        try {
            Files.copy(Paths.get(path), Paths.get(this.storage.getAbsolutePath() + File.separator + name + ".openquiz"), StandardCopyOption.REPLACE_EXISTING);
            t.pauseEnter("Datei erfolgreich importiert");
        } catch(MalformedURLException e) {
            Logger.error("Die angegebene URL ist nicht valide!");
        } catch(IOException e) {
            Logger.error("Fehler beim Zugriff auf das Dateisystem");
        } finally {
            t.pauseEnter("");
        }
    }

    /**
     * Writes an QuestionSet as an File
     * @param quests the used QuestionSet
     */
    public void importQuestionSet(QuestionSet quests) {
        Terminal t = OpenQuiz.getTerminal();
        String name = t.text("Speichern als: ");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.storage.getAbsolutePath() + File.separator + name + ".openquiz"));
            writer.write(quests.getTitle() + ";" + quests.getDescription() + ";" + quests.getDifficulty().toString());
            writer.newLine();

            // Fragen aus quests auslesen und in die Datei einfügen!

            for(int i = 0; i < quests.length; i++) {
                Question current = quests.get(i);
                String out = current.getQuestion() + ";" + current.getSolution();
                for(String answer : current.getAnswers()) {
                    out += (";" + answer);
                }
                writer.write(out);
                writer.newLine();
            }

            writer.flush();
            writer.close();

            Logger.info("Datei erfolgreich geschrieben!");
        } catch(Exception e) {
            Logger.error("Fehler beim schreiben der Datei!");
        }
    }
}
