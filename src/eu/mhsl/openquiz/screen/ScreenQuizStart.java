package eu.mhsl.openquiz.screen;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.out.Terminal;
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
        Terminal terminal = OpenQuiz.getTerminal();
        terminal.clearScreen();
        System.out.println("Name: " + Ansi.colorize(this.quiz.getTitle(), Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println("Beschreibung: " + Ansi.colorize(this.quiz.getDescription(), Attribute.BRIGHT_CYAN_TEXT()));
        System.out.println("Level: " + Ansi.colorize(this.quiz.getDifficulty().toString(), Attribute.CYAN_TEXT()));

        if(!terminal.bool("Möchtest du das Quiz spielen?")) return new ScreenQuizList();

        if(terminal.bool("Fragen durchmischen?")) quiz.randomize();

        int questioncount = 0;
        int playercount = terminal.number("Wie viele Spieler spielen das Spiel?");

        if(playercount > 1) {
            questioncount = terminal.number("Wie viele Fragen pro Fragenblock per Spieler?");
        }

        try {
            Hotseat playset = new Hotseat(playercount, questioncount, quiz);
            playset.beforeFirst();
            return new ScreenQuizQuestion(playset.next());
        } catch(Exception e) {
            Logger.error("Fehler beim erstellen des Playsets: " + e.getMessage());
            return new ScreenQuizList();
        }
    }
}
