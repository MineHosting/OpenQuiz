package eu.mhsl.openquiz.io;

import eu.mhsl.openquiz.Main;
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
    private File storage;
    public FileHandler() {
        this.storage = new File(System.getProperty("user.dir") + File.separator + "data");
        if(storage.mkdirs()) Logger.warn("Created 'data' directory");
    }

    public File[] getFiles() {
        return storage.listFiles();
    }

    public void importServer() {
        Terminal t = Main.getTerminal();
        t.text("Serverip: ", "127.0.0.1");
        t.number("Serverport: ", 8099);


    }

    public void importHTTP() {
        Terminal t = Main.getTerminal();
        String url = t.text("Link: ", "");
        String name = t.text("Speichern als: ", "Quizname");
        try {

            /**
             * Download, move and name target file
             */
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(this.storage.getAbsolutePath() + File.separator + name + ".openquiz"), StandardCopyOption.REPLACE_EXISTING);

        } catch(MalformedURLException e) {
            Logger.error("Die angegebene URL ist nicht valide!");
        } catch(IOException e) {
            Logger.error("Fehler beim Zugriff auf das Dateisystem");
        }
    }

    public void importFile() {
        Terminal t = Main.getTerminal();
        String path = t.text("File: ", "Absolut");
        String name = t.text("Speichern als: ", "Quizname");
        try {
            Files.copy(Paths.get(path), Paths.get(this.storage.getAbsolutePath() + File.separator + name + ".openquiz"), StandardCopyOption.REPLACE_EXISTING);
        } catch(MalformedURLException e) {
            Logger.error("Die angegebene URL ist nicht valide!");
        } catch(IOException e) {
            Logger.error("Fehler beim Zugriff auf das Dateisystem");
        }
    }
}
