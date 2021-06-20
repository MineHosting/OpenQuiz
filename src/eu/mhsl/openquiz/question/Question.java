package eu.mhsl.openquiz.question;

public class Question {
    private final QuestionDifficulty difficulty;
    private final String title;
    private final String question;
    private final String[] answers;
    private final int correct;

    /**
     * Constructor for an Question
     * @param title Displayed Question-title
     * @param quest the body of the Question
     * @param answers an Array of possible answers
     * @param correct the correct Answer as the Index of the answers parameter
     * @param difficulty Enum describing the Questiondifficulty
     */
    public Question(String title, String quest, String[] answers, int correct, QuestionDifficulty difficulty) {
        this.title = title;
        this.question = quest;
        this.answers = answers;
        this.correct = correct;
        this.difficulty = difficulty;
    }

    //self explaining get/setters
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
