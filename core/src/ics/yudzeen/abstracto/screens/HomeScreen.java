package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Add Class Description
 */

public class HomeScreen implements Screen {

    private static final String TAG = HomeScreen.class.getName();

    private Abstracto game;

    private SpriteBatch batch;
    private OrthographicCamera gameCamera;
    private Viewport gameViewport;

    private BitmapFont font;

    public HomeScreen(Abstracto game) {
        this.game = game;
        init();
    }

    public void init() {
        batch = new SpriteBatch();
        gameCamera = new OrthographicCamera();
        gameViewport = new StretchViewport(GameConstants.WIDTH, GameConstants.HEIGHT, gameCamera);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(gameCamera.combined);

        batch.begin();
        font.draw(batch, GameConstants.GAME_TITLE, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
