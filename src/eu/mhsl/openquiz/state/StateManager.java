package eu.mhsl.openquiz.state;


import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.screen.Screen;

public class StateManager {
    public void setScreen(Screen s) {
        try {
            Screen next = s.display();
            if(next != null) setScreen(next);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("Es ist ein fehler in OpenQuiz aufgetreten: " + e.getMessage());
        }
    }
}
