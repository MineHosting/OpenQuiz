package eu.mhsl.openquiz.state;

import eu.mhsl.openquiz.out.Logger;
import eu.mhsl.openquiz.question.Question;
import eu.mhsl.openquiz.question.QuestionSet;

import java.util.HashMap;
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

    private int pointer = 0;
    private final TreeMap<Integer, TreeMap<Integer, Question>> playset = new TreeMap<>();
    private final HashMap<Integer, Integer> playerpoints = new HashMap<>();

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

        //set points for every player to 0
        for(int i = 0; i < this.playercount; i++) {
            this.playerpoints.put(i, 0);
        }

        //generate playmap
        //brainfuck
        int questindex;
        int runindex = 0;
        if(playercount > 1 && questioncount > 1) {
            //multiplayer, multiquestion
            outer:
            for(int loops = 1; true; loops++) {
                for(int players = 0; players < this.playercount; players++) {
                    for(int quests = 0; quests < this.questioncount; quests++) {
                        if(loops+quests+(loops-1) > this.questions.length) break outer;
                        questindex = (loops+quests+(loops-1));
                        runindex++;

                        this.setPlayset(runindex, players, this.questions.get(questindex-1));
                    }
                }
            }
        } else if(playercount > 1 && questioncount == 1) {
            //multiplayer but singlequestion
            for(int quests = 0; quests < this.questions.length; quests++) {
                for(int players = 0; players < this.playercount; players++) {
                    questindex = quests+1;
                    runindex++;
                    this.setPlayset(runindex, players, this.questions.get(questindex-1));
                }
            }
        } else if(playercount == 1) {
            //singleplayer
            for(int quests = 0; quests < this.questions.length; quests++) {
                questindex = quests+1;
                runindex++;
                this.setPlayset(runindex, 0, this.questions.get(questindex-1));
            }

        }

        Logger.warn(playset.toString());
    }

    /**
     * Set Pointer before the first Element
     */
    public void beforeFirst() {
        this.pointer = 0;
    }

    /**
     * move Pointer to the next position
     * @return the 'new' Hotseat element with the new Position
     */
    public Hotseat next() {
        this.pointer++;
        return this;
    }

    /**
     * Get the Map from the current pointer
     * @return the Map containing the current Player and the Question
     */
    public TreeMap get() {
        return this.playset.get(this.pointer);
    }

    /**
     * Get the position of the Pointer
     * @return position
     */
    public int getPointer() {
        return this.pointer;
    }

    /**
     * Get the current Question
     * @return Question
     */
    public Question getQuestion() {
        return this.playset.get(this.pointer).values().toArray(new Question[0])[0];
    }

    /**
     * Get the current Player
     * @return player number
     */
    public int getPlayer() {
        return this.playset.get(this.pointer).keySet().toArray(new Integer[0])[0];
    }

    /**
     * Add an Point to the current Player
     */
    public void addPoint() {
        Logger.info(this.playerpoints.toString());
        this.playerpoints.put(this.getPlayer(), this.playerpoints.get(this.getPlayer()) + 1);
    }

    /**
     * Get all Points
     * @return Map with the Playerindex and his points
     */
    public HashMap<Integer, Integer> getPoints() {
        return this.playerpoints;
    }

    /**
     * Add internally an Playset to the Array
     * @param index index of Playaction
     * @param player player id
     * @param quest the Question to answer
     */
    private void setPlayset(int index, int player, Question quest) {
        TreeMap<Integer, Question> playerQuest = new TreeMap<>();
        playerQuest.put(player, quest);

        this.playset.put(index, playerQuest);
    }

    /**
     * get the Amount of configured Players
     * @return amount of players
     */
    public int getPlayercount() {
        return this.playercount;
    }

}
