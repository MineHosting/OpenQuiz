package eu.mhsl.openquiz.screen;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.Main;
import eu.mhsl.openquiz.question.QuestionSet;

import java.io.File;
import java.util.TreeMap;

public class ScreenQuizStart implements Screen {
    private QuestionSet quiz;

    public ScreenQuizStart(QuestionSet quiz) {
        this.quiz = quiz;

    }
    public Screen display() {
        Main.getTerminal().clearScreen();
        System.out.println("Name: " + Ansi.colorize(this.quiz.getTitle(), Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println("Level: " + Ansi.colorize(this.quiz.getDifficulty().toString(), Attribute.CYAN_TEXT()));
        return null;
    }
}
