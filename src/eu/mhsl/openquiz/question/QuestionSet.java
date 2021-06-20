package eu.mhsl.openquiz.question;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionSet {
    private int pointer = 0;
    private ArrayList<Question> questions = new ArrayList<>();

    private String title;
    private String description;
    private QuestionDifficulty difficulty;

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
        this.difficulty = QuestionDifficulty.EASY;

        questions.add(new Question("Testfrage", "Beantworte die Frage", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 1, QuestionDifficulty.EASY));
        questions.add(new Question("Testfrage2", "Beantworte die Frage2", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 0, QuestionDifficulty.EASY));
        questions.add(new Question("Testfrage3", "Beantworte die Frage3", new String[] {"Antwort1", "Antwort2", "Antwort3"}, 3, QuestionDifficulty.EASY));


        this.length = questions.size();
    }

    /**
     * Move internal Pointer and test for end of List
     * @return is true if there is an Next-Entry
     */
    public Boolean next() {
        this.pointer++;
        return this.getCurrent() != null;
    }

    /**
     * Move the internal Pointer before the Start of the first Element
     */
    public void beforeFirst() {
        this.pointer = -1;
    }

    /**
     * Move the internal Pointer to an specific offset. Negative numbers for back
     * @param offset integer offset
     */
    public void moveCurrent(int offset) {
        this.pointer += offset;
    }

    /**
     * Get the current Item from current Pointer position
     * @return returns Question the pointer is viewing
     */
    public Question getCurrent() {
        return this.questions.get(this.pointer);
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
    public QuestionDifficulty getDifficulty() {
        return this.difficulty;
    }


}
