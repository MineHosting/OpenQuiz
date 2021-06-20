package eu.mhsl.openquiz.screen;

import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.question.QuestionSet;
import eu.mhsl.openquiz.state.Hotseat;

/**
 * Implements one specific quiz-Question
 */
public class ScreenQuizQuestion implements Screen {
    private QuestionSet questions;
    private Hotseat hotseat;

    public ScreenQuizQuestion(QuestionSet questions, Hotseat hotseat) {
        this.questions = questions;
        this.hotseat = hotseat;
    }

    public Screen display() {
        Logger.warn("Finished exec");
        return null;
    }
}
