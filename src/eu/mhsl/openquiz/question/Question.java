package eu.mhsl.openquiz.question;

public class Question {
    private final String question;
    private final String[] answers;
    private final int correct;

    /**
     * Constructor for an Question
     * @param quest the body of the Question
     * @param answers an Array of possible answers
     * @param correct the correct Answer as the Index of the answers parameter
     */
    public Question(String quest, String[] answers, int correct) {
        this.question = quest;
        this.answers = answers;
        this.correct = correct;
    }

    //self explaining get/setters
    public String getQuestion() {
        return this.question;
    }
    public String[] getAnswers() {
        return this.answers;
    }
    public int getSolution() {
        return this.correct;
    }
}
