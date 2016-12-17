package ics.yudzeen.abstracto;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import ics.yudzeen.abstracto.screens.HomeScreen;

public class Abstracto extends Game {

	private static final String TAG = Abstracto.class.getName();

	HomeScreen homeScreen;

	boolean paused;

	@Override
	public void create () {
		homeScreen = new HomeScreen(this);
		setScreen(homeScreen);
		paused = false;
	}

	@Override
	public void render () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void pause() {
		paused = true;
		super.pause();
	}

	@Override
	public void resume() {
		paused = false;
		super.resume();
	}

	@Override
	public void dispose () {

	}
}
