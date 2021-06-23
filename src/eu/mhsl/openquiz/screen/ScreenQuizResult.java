package eu.mhsl.openquiz.screen;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import eu.mhsl.openquiz.OpenQuiz;
import eu.mhsl.openquiz.out.Terminal;
import eu.mhsl.openquiz.state.Hotseat;

import java.util.HashMap;

public class ScreenQuizResult implements Screen {
    Hotseat configuration;
    public ScreenQuizResult(Hotseat hs) {
        this.configuration = hs;
    }
    public Screen display() {
        HashMap<Integer, Integer> points = this.configuration.getPoints();

        Terminal terminal = OpenQuiz.getTerminal();
        terminal.clearScreen();
        System.out.println(Ansi.colorize("Spielergebnis:", Attribute.GREEN_TEXT()));
        if(configuration.getPlayercount() > 1) {
            for(int p = 0; p < points.size(); p++) {
                System.out.println("Spieler " + p + " : " + Ansi.colorize(points.get(p) + "", Attribute.CYAN_TEXT()));
            }
        } else {
            System.out.println("Deine Punkte : " + Ansi.colorize(points.get(0) + "", Attribute.CYAN_TEXT()));
        }

        if(terminal.bool("Möchtest du Openquiz beenden?")) return null;

        return new ScreenQuizList();
    }
}
