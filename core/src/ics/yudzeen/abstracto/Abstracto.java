package ics.yudzeen.abstracto;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ics.yudzeen.abstracto.screens.TitleScreen;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Main game class file
 */


// TODO: 07/05/2017 Simulator Instructions
public class Abstracto extends Game {

	public static final String TAG = Abstracto.class.getName();

	private SpriteBatch batch;
	private Assets assets;
	private GamePreferences gamePreferences;

	public AndroidInterfaces androidInterfaces;

	public Abstracto(AndroidInterfaces androidInterfaces) {
		this.androidInterfaces = androidInterfaces;
	}

	@Override
	public void create () {
		assets = Assets.getInstance();
		assets.init(new AssetManager());

		gamePreferences = GamePreferences.instance;
		gamePreferences.load();

		batch = new SpriteBatch();
		Gdx.input.setCatchBackKey(true);

		setScreen(new TitleScreen(this));

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Assets getAssets() { return assets; }

	public GamePreferences getGamePreferences() { return gamePreferences; }

	@Override
	public void dispose() {
		assets.dispose();
		batch.dispose();
	}
}
