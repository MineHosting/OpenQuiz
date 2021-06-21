package eu.mhsl.openquiz.state;

import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.question.Question;
import eu.mhsl.openquiz.question.QuestionSet;

import java.util.TreeMap;

/**
 * Hotseat is an representation of the Game procedure itself.
 * It manages different Players and when what player has to answer a question.
 * The procedure is once generated in the constructor.
 */
public class Hotseat {
    protected int playercount;
    protected int questioncount;
    protected QuestionSet questions;

    private final int pointer = 0;
    private final TreeMap<Integer, Question> playset = new TreeMap<>();

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

        //brainfuck
        int questindex;
        if(playercount > 1 && questioncount > 1) {
            outer:
            for(int loops = 1; true; loops++) {
                for(int players = 0; players < this.playercount; players++) {
                    for(int quests = 0; quests < this.questioncount; quests++) {
                        if(loops+quests+(loops-1) > this.questions.length) break outer;
                        questindex = (loops+quests+(loops-1));
                        Logger.info("Player/Quest:" + players + " " + questindex);
                    }
                }
            }
        } else if(playercount > 1 && questioncount == 1) {
            for(int quests = 0; quests < this.questions.length; quests++) {
                for(int players = 0; players < this.playercount; players++) {
                    questindex = quests+1;
                    Logger.info("Player/Quest:" + players + " " + questindex);
                }
            }
        } else if(playercount == 1) {
            for(int quests = 0; quests < this.questions.length; quests++) {
                questindex = quests+1;
                Logger.info("Player/Quest:0 " + questindex);
            }
        }

    }
}
