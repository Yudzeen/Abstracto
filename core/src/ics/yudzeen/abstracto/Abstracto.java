package ics.yudzeen.abstracto;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ics.yudzeen.abstracto.screens.MenuScreen;
import ics.yudzeen.abstracto.utils.Assets;

public class Abstracto extends Game {

	public static final String TAG = Abstracto.class.getName();

	private SpriteBatch batch;
	private Assets assets;

	@Override
	public void create () {
		assets = Assets.getInstance();
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(74/255.0f,143/255.0f,231/255.0f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Assets getAssets() { return assets; }

}
