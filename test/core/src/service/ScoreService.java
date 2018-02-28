package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Stefcio on 15.09.2017.
 */
public class ScoreService {

    public final static String GAME_PREFS = "com.mygdx.game.prefs";
    public final static String GAME_SCORE = "com.mygdx.game.prefs.score";
    public final static String GAME_STRENGTH = "com.mygdx.game.prefs.strength";
    public final static String GAME_KILLED_MONSTERS = "com.mygdx.game.prefs.killed.monsters";
    public final static String GAME_LEVEL = "com.mygdx.game.prefs.level";
    public final static String GAME_XP = "com.mygdx.game.prefs.xp";
    public final static String GAME_DEPTH = "com.mygdx.game.prefs.depth";
    public final static String GAME_REQUIREDXP = "com.mygdx.game.prefs.requiredxp";
    public final static String GAME_ATTRIBUTES = "com.mygdx.game.prefs.attributes";
    public final static String GAME_DEXTERITY = "com.mygdx.game.prefs.dexterity";



    private int gainedxp;
    private int attributesToAdd;
    public int getGainedxp() {
        return gainedxp;
    }


    private int points;
    private int strength;
    private int attributes;
    private Preferences prefs;
    private int killedMonsters;
    private int depth;
    private int xp;
    private int level;
    private int requiredxp;
    private int dexterity;

    public int getAttributesToAdd() {
        return attributesToAdd;
    }

    public void setAttributesToAdd(int attributesToAdd) {
        this.attributesToAdd = attributesToAdd;
    }







    public ScoreService() {
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
    }
    public void addXp(){
        int calcxp = (int) (Math.pow(depth, 1.1))*10;
        int calcmin = (int) (calcxp*(80.0f/100.0f));
        int calcmax = (int) (calcxp*(120.0f/100.0f));
        gainedxp = MathUtils.random(calcmin, calcmax);
        System.out.println("min exp: " + calcmin);
        System.out.println("max exp: " + calcmax);
        System.out.println("You've gained " + gainedxp + " xp!");
        xp += gainedxp;
        requiredxp = (int) Math.pow(level, 2)*100;
        updateXpInPrefs();
        updateRequiredXpInPrefs();

    }
    private void updateXpInPrefs() {
        prefs.putInteger(GAME_XP, xp);
        prefs.flush();
    }



    private void updateRequiredXpInPrefs() {
        prefs.putInteger(GAME_REQUIREDXP, requiredxp);
        prefs.flush();
    }

    public void addLevel() {
        if (xp >= requiredxp) {
            xp -= requiredxp;
            updateXpInPrefs();
            updateRequiredXpInPrefs();
            level++;
            System.out.println("You've gained new level! Your level is: " + level);
            updateLevelInPrefs();
        }
    }

    private void updateLevelInPrefs() {
        prefs.putInteger(GAME_LEVEL, level);
        prefs.flush();
    }


    public void addPoint() {
        points++;
        updateSavedScoreInPrefs();
    }

    public void addPoints(int pointsToAdd) {
        points += pointsToAdd;
        updateSavedScoreInPrefs();
    }

    public void addStrength(int attributesToAdd){
        if (attributes >= attributesToAdd){
            strength += attributesToAdd;
            attributes -= attributesToAdd;
            updateAttributesInPrefs();
            updateStrengthInPrefs();
        }
        else {
            strength += attributes;
            attributes = 0;
            updateAttributesInPrefs();
            updateStrengthInPrefs();
        }

    }
    public void addDexterity(int attributesToAdd){
        if (attributes >= attributesToAdd){
            dexterity += attributesToAdd;
            attributes-= attributesToAdd;
            updateAttributesInPrefs();
            updateDexterityInPrefs();
        }
        else {
            dexterity += attributes;
            attributes = 0;
            updateAttributesInPrefs();
            updateDexterityInPrefs();
        }
    }


    public void addAttribute(){
            if (points>=100) {
                attributes += level;
                points -= 100;
                updateSavedScoreInPrefs();
                updateAttributesInPrefs();


            }
    }
        private void updateAttributesInPrefs(){
            prefs.putInteger(GAME_ATTRIBUTES, attributes);
            prefs.flush();
        }

        public void addKilledMonsters(){
        killedMonsters++;
        updateKilledMonstersInPrefs();
        }
        public void increaseDepth(){
                depth++;
                killedMonsters = 0;
                System.out.println("Depth increased!");
                updateDepthInPrefs();
                updateKilledMonstersInPrefs();
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
    private void updateDexterityInPrefs(){
        prefs.putInteger(GAME_DEXTERITY, dexterity);
        prefs.flush();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
        strength = prefs.getInteger(GAME_STRENGTH);
        dexterity = prefs.getInteger(GAME_DEXTERITY);
        killedMonsters = prefs.getInteger(GAME_KILLED_MONSTERS);
        depth = prefs.getInteger(GAME_DEPTH);
        xp = prefs.getInteger(GAME_XP);
        level = prefs.getInteger(GAME_LEVEL);
        attributes = prefs.getInteger(GAME_ATTRIBUTES);
        attributesToAdd = 1;
        newPlayer();

    }

    private void newPlayer() {
        if (level > 1) {
            level = prefs.getInteger(GAME_LEVEL);

        }
        else {
            level = 1;
            updateLevelInPrefs();

        }

        if(strength > 1){
            strength = prefs.getInteger(GAME_STRENGTH);

        }
        else {
            strength = 1;
            updateStrengthInPrefs();
        }
        if(dexterity > 1){
            dexterity = prefs.getInteger(GAME_DEXTERITY);

        }
        else {
            dexterity = 1;
            updateDexterityInPrefs();
        }
        if(depth > 1){
            depth = prefs.getInteger(GAME_DEPTH);

        }
        else {
            depth = 1;
            updateDepthInPrefs();
        }
    }


    public int getPoints() {
        return points;
    }

    public int getStrength(){
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getAttributes() {
        return attributes;
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

    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getRequiredxp() {
        return requiredxp;
    }

    public void setRequiredxp(int requiredxp) {
        this.requiredxp = requiredxp;
    }


    public void resetGamePoints() {
        points = 0;
        strength = 1;
        attributes = 0;
        killedMonsters = 0;
        depth = 0;
        xp = 0;
        level = 1;
        updateSavedScoreInPrefs();
        updateStrengthInPrefs();
        updateDexterityInPrefs();
        updateAttributesInPrefs();
        updateKilledMonstersInPrefs();
        updateDepthInPrefs();
        updateXpInPrefs();
        updateLevelInPrefs();
    }



}
