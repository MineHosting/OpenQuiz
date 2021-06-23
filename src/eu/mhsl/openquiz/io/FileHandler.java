package eu.mhsl.openquiz.io;

import eu.mhsl.openquiz.out.Logger;

import java.io.File;

public class FileHandler {
    private File storage;
    public FileHandler() {
        this.storage = new File(System.getProperty("user.dir") + File.separator + "data");
        if(storage.mkdirs()) Logger.warn("Created 'data' directory");
    }

    public File[] getFiles() {
        return storage.listFiles();
    }

}
