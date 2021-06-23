package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.OpenQuiz;
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
        boolean correct = OpenQuiz.getTerminal().quizQuestion(hotseat.getQuestion(), hotseat.getPointer(), hotseat.getPlayer());

        if(correct) hotseat.addPoint();
        if(correct) Logger.warn("Correct!");

        Hotseat next = hotseat.next();
        if(next.get() == null) return new ScreenQuizResult(hotseat);
        return new ScreenQuizQuestion(next);
    }
}
