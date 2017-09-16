package com.myGdxGame.game;

import com.badlogic.gdx.Game;
import screens.SplashScreen;
import service.ScoreService;

public class MyGdxGame extends Game {


    public final static String GAME_NAME = "Tutorial Clicker";

    public final static int WIDTH = 480;
    public final static int HEIGHT = 700;


    private ScoreService scoreService;


	/*
    * Getters and Setters
	*
	* */

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private boolean paused;

    public ScoreService getScoreService() {
        return scoreService;
    }



    @Override
    public void create() {
        init();
        this.setScreen(new SplashScreen(this));

    }

    private void init() {
        initScoreService();

    }

    private void initScoreService() {
        scoreService = new ScoreService();
    }


}
