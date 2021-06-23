package eu.mhsl.openquiz.screen;

public class ScreenQuizCreator implements Screen {

    public Screen display() {

        return new ScreenQuizList();
    }
}
