package eu.mhsl.openquiz.io;

import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.out.Terminal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHandler {
    private final File storage;
    public FileHandler() {
        this.storage = new File(System.getProperty("user.dir") + File.separator + "data");
        if(storage.mkdirs()) Logger.warn("Created 'data' directory");
    }

    public File[] getFiles() {
        return storage.listFiles();
    }

    public void importHTTP() {
        Terminal t = OpenQuiz.getTerminal();
        String url = t.text("Link: ", "");
        String name = t.text("Speichern als: ", "Quizname");
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

    public void importFile() {
        Terminal t = OpenQuiz.getTerminal();
        String path = t.text("File: ", "Absolut");
        String name = t.text("Speichern als: ", "Quizname");
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
}
