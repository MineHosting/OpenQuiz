package eu.mhsl.openquiz.state;

import eu.mhsl.openquiz.screen.Screen;

public class StateManager {
    private Screen screen;

    public void setScreen(Screen s) {
        this.screen = s;
        Screen next = s.display();
        if(next != null) setScreen(next);
    }
}
