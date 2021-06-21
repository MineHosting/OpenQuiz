package eu.mhsl.openquiz.screen;

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
        System.out.println(hotseat.get().toString());

        Hotseat next = hotseat.next();
        if(next.get() == null) return null;
        return new ScreenQuizQuestion(next);
    }
}
