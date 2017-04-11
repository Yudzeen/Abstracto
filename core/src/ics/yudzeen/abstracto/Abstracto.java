package ics.yudzeen.abstracto;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;

import ics.yudzeen.abstracto.screens.DirectedGame;
import ics.yudzeen.abstracto.screens.TitleScreen;
import ics.yudzeen.abstracto.screens.transitions.Fade;
import ics.yudzeen.abstracto.screens.transitions.ScreenTransition;
import ics.yudzeen.abstracto.screens.transitions.Slice;
import ics.yudzeen.abstracto.screens.transitions.Slide;
import ics.yudzeen.abstracto.utils.Assets;

/**
 * Main game class file
 */

// TODO: 03/04/2017 transition
// TODO: 04/04/2017 revamp ui

public class Abstracto extends DirectedGame {

	public static final String TAG = Abstracto.class.getName();

	private SpriteBatch batch;
	private Assets assets;

	public AndroidInterfaces androidInterfaces;

	public Abstracto(AndroidInterfaces androidInterfaces) {
		this.androidInterfaces = androidInterfaces;
	}

	@Override
	public void create () {
		assets = Assets.getInstance();
		assets.init(new AssetManager());
		batch = new SpriteBatch();
		Gdx.input.setCatchBackKey(true);

		ScreenTransition transition = Slice.init(2, Slice.UP_DOWN, 10, Interpolation.pow5Out);
		//ScreenTransition transition = Fade.init(2);
		//ScreenTransition transition = Slide.init(2, Slide.UP, true, Interpolation.pow5Out);
		setScreen(new TitleScreen(this), transition);

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Assets getAssets() { return assets; }

}
