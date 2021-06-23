package eu.mhsl.openquiz.screen;

/**
 * Interface for representing an Console-Screen
 */
public interface Screen {
    /**
     * Runs the specific implementation
     * @return the implementation can "redirect" to the next screen or can return simply null
     */
    Screen display() throws Exception;
}
