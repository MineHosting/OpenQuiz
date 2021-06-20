package eu.mhsl.openquiz.state;

import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.question.Question;
import eu.mhsl.openquiz.question.QuestionSet;

import java.util.TreeMap;

/**
 * Hotseat is an representation of the Game procedure itself.
 * It manages different Players and when what player has to answer a question.
 * The procedure is customizable by the constructor parameters
 */
public class Hotseat {
    protected int playercount;
    protected int questioncount;
    protected QuestionSet questions;

    private int pointer = 0;
    private TreeMap<Integer, Question> playset = new TreeMap<>();

    /**
     * Create an Game-Procedure managing different Players and Questions
     * @param playercount Count of playing Players
     * @param questioncount How many Questions should be answered by each Player before the "Seat" is switched?
     *                      So you don't have to switch every Question
     * @param questions Questionset of target questions
     */
    public Hotseat(int playercount, int questioncount, QuestionSet questions) {
        this.playercount = playercount;
        this.questioncount = questioncount;
        this.questions = questions;


        //generate dataset
        this.questions.beforeFirst();

         for(int quest = 0; quest <= this.questions.length; quest++) {
             Logger.info("Question:"+quest);
         }

    }


}
