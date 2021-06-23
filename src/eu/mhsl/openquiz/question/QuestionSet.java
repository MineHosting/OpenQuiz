package eu.mhsl.openquiz.question;

import eu.mhsl.openquiz.out.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InvalidPropertiesFormatException;
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

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String[]> items = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) continue;
                items.add(line.split(";"));
            }


            this.title = items.get(0)[0];
            this.description = items.get(0)[1];
            this.difficulty = QuizDifficulty.valueOf(items.get(0)[2]);

            for(int q = 1; q < items.size(); q++) {
                String[] current = items.get(q);
                String[] rawquests = new String[current.length-2];

                for(int j = 0; j < current.length-2; j++){
                    rawquests[j] = current[j+2];
                }

                Logger.warn("-");

                questions.add(new Question(current[0], rawquests, Integer.parseInt(current[1])));
            }

        } catch(Exception e) {
            Logger.error("Error while reading quiz, check for correct Syntax!");
            Logger.error("Failed to parse file " + file.getAbsolutePath());
            throw new RuntimeException();
        }


        //TODO: create Questionlist from File


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
