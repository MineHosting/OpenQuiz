package eu.mhsl.openquiz.screen;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.Main;
import eu.mhsl.openquiz.question.QuestionSet;
import eu.mhsl.openquiz.state.Hotseat;

/**
 * Implements the Quiz-Config and Start screen
 */
public class ScreenQuizStart implements Screen {
    private final QuestionSet quiz;

    public ScreenQuizStart(QuestionSet quiz) {
        this.quiz = quiz;

    }
    public Screen display() {
        Main.getTerminal().clearScreen();
        System.out.println("Name: " + Ansi.colorize(this.quiz.getTitle(), Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println("Beschreibung: " + Ansi.colorize(this.quiz.getDescription(), Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println("Level: " + Ansi.colorize(this.quiz.getDifficulty().toString(), Attribute.CYAN_TEXT()));

        if(Main.getTerminal().bool("Möchtest du das Quiz starten?")) {
            Hotseat playset = new Hotseat(1, 1, quiz);
            playset.beforeFirst();
            return new ScreenQuizQuestion(playset.next());
        } else {
            return new ScreenQuizList();
        }
    }
}
