package eu.mhsl.openquiz;

import eu.mhsl.openquiz.io.FileHandler;
import eu.mhsl.openquiz.out.Terminal;
import eu.mhsl.openquiz.screen.ScreenOpening;
import eu.mhsl.openquiz.state.StateManager;

/**
 * Welcome to OpenQuiz
 * This is an simple Terminal based Quiz-Application obviously written in Java
 * You can create, download, host, play, and manage your own Quizzes
 */
public class OpenQuiz {
    private static Terminal terminal;
    private static StateManager stateManager;
    private static FileHandler files;

    public static void main(String[] args) {
        //creating often used Instances of classes
        terminal = new Terminal();
        files = new FileHandler();
        stateManager = new StateManager();

        stateManager.setScreen(new ScreenOpening()); //Start starting Screen

    }

    //Simple getters
    public static Terminal getTerminal() {
        return terminal;
    }
    public static StateManager getStateManager() {
        return stateManager;
    }
    public static FileHandler getFileHandler() {
        return files;
    }
}
