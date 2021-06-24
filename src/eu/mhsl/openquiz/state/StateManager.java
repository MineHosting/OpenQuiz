package eu.mhsl.openquiz.state;


import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.screen.Screen;

/**
 * The StateManager saves the current Display state and opens an new Screen if necessary.
 * This class could be simply implemented in the Main-Class but as an extra-class it is more open to future changes.
 */
public class StateManager {
    /**
     * Sets an 'startscreen' and opens new Screen if provided by previous Screen
     * @param s the Startscreen
     */
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
