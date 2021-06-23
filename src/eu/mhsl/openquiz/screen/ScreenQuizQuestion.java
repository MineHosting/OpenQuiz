package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.Main;
import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.state.Hotseat;

/**
 * Implements one specific quiz-Question
 */
public class ScreenQuizQuestion implements Screen {
    private final Hotseat hotseat;

    public ScreenQuizQuestion(Hotseat hotseat) {
        this.hotseat = hotseat;
    }

    public Screen display() {
        boolean correct = Main.getTerminal().quizQuestion(hotseat.getQuestion(), 1, hotseat.getPlayer());

        if(correct)
            hotseat.addPoint();
        else
            hotseat.removePoint();

        Hotseat next = hotseat.next();
        if(next.get() == null) return new ScreenQuizResult(hotseat);
        return new ScreenQuizQuestion(next);
    }
}
