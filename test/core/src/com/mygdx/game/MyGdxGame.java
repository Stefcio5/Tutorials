package com.mygdx.game;

import com.badlogic.gdx.Game;
import screens.SplashScreen;

public class MyGdxGame extends Game {

	public final static String GAME_NAME = "Tutorial Clicker";

	public final static int WIDTH = 480;
	public final static int HEIGHT = 700;


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




	
	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));

	}



	

}
