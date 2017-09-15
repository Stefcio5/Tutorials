package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Stefcio on 15.09.2017.
 */
public class ScoreService {

    public final static String GAME_PREFS = "com.mygdx.game.prefs";
    public final static String GAME_SCORE = "com.mygdx.game.prefs.score";


    private int points;
    private Preferences prefs;

    public ScoreService(){
        init();
    }
    private void init(){
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
    }


    public void addPoint() {
        points++;
        updateSavedScoreInPrefs();
    }
    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
        updateSavedScoreInPrefs();
    }

    private void updateSavedScoreInPrefs() {
        prefs.putInteger(GAME_SCORE, points);
        prefs.flush();
    }
    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);

    }




    public int getPoints() {
        return points;
    }

    public void resetGamePoints() {
        points = 0;
        updateSavedScoreInPrefs();
    }
}
