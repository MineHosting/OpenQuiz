package eu.mhsl.openquiz.question;

public class Question {
    private QuestionDifficulty difficulty;
    private String title;
    private String question;
    private String[] answers;
    private int correct;

    /**
     * Constructor for an Question
     * @param title
     * @param quest the body of the Question
     * @param answers an Array of possible answers
     * @param correct the correct Answer as the Index of the answers parameter
     * @param difficulty
     */
    public Question(String title, String quest, String[] answers, int correct, QuestionDifficulty difficulty) {
        this.title = title;
        this.question = quest;
        this.answers = answers;
        this.correct = correct;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return this.title;
    }
    public String getQuestion() {
        return this.question;
    }
    public String[] getAnswers() {
        return this.answers;
    }
    public int getSolution() {
        return this.correct;
    }
    public QuestionDifficulty getDifficulty() {
        return this.difficulty;
    }
}
