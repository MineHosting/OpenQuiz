package eu.mhsl.openquiz.question;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionSet {
    private ArrayList<Question> questions = new ArrayList<>();

    private String title;
    private String description;
    private QuizDifficulty difficulty;

    public int length;

    /**
     * Construct an QuestionList from an ArrayList
     * @param quests Arraylist of predefined questions
     */
    public QuestionSet(ArrayList<Question> quests) {
        this.questions = quests;
    }

    /**
     * Construct an QuestionList from an json-String
     * @param file an File containing the openquiz format
     */
    public QuestionSet(File file) {
        //TODO: create Questionlist from File

        this.title = "Testquiz";
        this.description = "Das ist ein kurzes Testquiz";
        this.difficulty = QuizDifficulty.EASY;

        questions.add(new Question("Testfrage", "Beantworte die Frage", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 1));
        questions.add(new Question("Testfrage2", "Beantworte die Frage2", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 0));
        questions.add(new Question("Testfrage3", "Beantworte die Frage3", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 3));
        questions.add(new Question("Testfrage", "Beantworte die Frage", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 1));
        questions.add(new Question("Testfrage2", "Beantworte die Frage2", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 0));
        questions.add(new Question("Testfrage3", "Beantworte die Frage3", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 3));


        this.length = questions.size();
    }

    /**
     * get an Question by index
     * @param index index
     * @return the corresponding question
     */
    public Question get(int index) {
        return questions.get(index);
    }

    /**
     * Shuffle the Questions in the set
     */
    public void randomize() {
        Collections.shuffle(this.questions, new Random());
    }


    //further self explaining getters
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public QuizDifficulty getDifficulty() {
        return this.difficulty;
    }


}
