package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Stefcio on 15.09.2017.
 */
public class ScoreService {

    public final static String GAME_PREFS = "com.mygdx.game.prefs";
    public final static String GAME_SCORE = "com.mygdx.game.prefs.score";
    public final static String GAME_STRENGTH = "com.mygdx.game.prefs.strength";
    public final static String GAME_KILLED_MONSTERS = "com.mygdx.game.prefs.killed.monsters";
    public final static String GAME_DEPTH = "com.mygdx.game.prefs.depth";



    private int points;
    private int strength;
    private Preferences prefs;
    private int killedMonsters;
    private int depth;

    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
    }


    public void addPoint() {
        points++;
        updateSavedScoreInPrefs();
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;

        updateSavedScoreInPrefs();
    }
    public void addStrength(){
        if (points>=100) {
            strength++;
            points -= 100;
            updateSavedScoreInPrefs();
            updateStrengthInPrefs();
        }
        }
        public void addKilledMonsters(){
        killedMonsters++;
        updateKilledMonstersInPrefs();
        }
        public void increaseDepth(){
            if(killedMonsters == 10){
                depth++;
                killedMonsters = 0;
                System.out.println("Depth increased!");
                updateDepthInPrefs();
                updateKilledMonstersInPrefs();

            }
        }

    private void updateDepthInPrefs() {
            prefs.putInteger(GAME_DEPTH, depth);
            prefs.flush();
    }

    private void updateKilledMonstersInPrefs() {
            prefs.putInteger(GAME_KILLED_MONSTERS, killedMonsters);
            prefs.flush();
    }


    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.flush();
    }
    private void updateStrengthInPrefs(){
        prefs.putInteger(GAME_STRENGTH, strength);
        prefs.flush();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
        strength = prefs.getInteger(GAME_STRENGTH);
        killedMonsters = prefs.getInteger(GAME_KILLED_MONSTERS);
        depth = prefs.getInteger(GAME_DEPTH);

    }


    public int getPoints() {
        return points;
    }
    public int getStrength(){
        return strength;
    }
    public int getKilledMonsters() {
        return killedMonsters;
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


    public void resetGamePoints() {
        points = 0;
        strength = 1;
        killedMonsters = 0;
        depth = 0;
        updateSavedScoreInPrefs();
        updateStrengthInPrefs();
        updateKilledMonstersInPrefs();
        updateDepthInPrefs();
    }



}
