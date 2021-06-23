package eu.mhsl.openquiz;

import eu.mhsl.openquiz.io.FileHandler;
import eu.mhsl.openquiz.out.Terminal;
import eu.mhsl.openquiz.screen.ScreenOpening;
import eu.mhsl.openquiz.state.StateManager;

public class OpenQuiz {
    private static Terminal terminal;
    private static StateManager stateManager;
    private static FileHandler files;

    public static void main(String[] args) {
        terminal = new Terminal();
        files = new FileHandler();
        stateManager = new StateManager();
        stateManager.setScreen(new ScreenOpening()); //Start starting Screen

    }

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
