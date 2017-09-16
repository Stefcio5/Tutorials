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



    private int points;
    private int strength;
    private Preferences prefs;

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

    }


    public int getPoints() {
        return points;
    }
    public int getStrength(){
        return strength;
    }

    public void resetGamePoints() {
        points = 0;
        updateSavedScoreInPrefs();
    }
}
