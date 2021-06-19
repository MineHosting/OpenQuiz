package eu.mhsl.openquiz;

import eu.mhsl.openquiz.out.Terminal;
import eu.mhsl.openquiz.screen.ScreenOpening;
import eu.mhsl.openquiz.state.StateManager;

public class Main {
    private static Terminal terminal;
    private static StateManager stateManager;

    public static void main(String[] args) {
        terminal = new Terminal();
        stateManager = new StateManager();
        stateManager.setScreen(new ScreenOpening()); //Start starting Screen

    }

    public static Terminal getTerminal() {
        return terminal;
    }
    public static StateManager getStateManager() {
        return stateManager;
    }
}
