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

        this.title = "Betriebsysteme";
        this.description = "Wie kennst du dich in der Welt der Betriebsysteme aus?";
        this.difficulty = QuizDifficulty.EASY;

        questions.add(new Question("Windows", "Wer hat Windows erfunden?", new String[] {"Linus Torvalds", "Bill Gates", "Henry Mickenbecker"}, 1));
        questions.add(new Question("Linux", "Wer hat Linux erfunden", new String[] {"Bill Gates", "David Maul", "Linux Torvalds"}, 2));

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
