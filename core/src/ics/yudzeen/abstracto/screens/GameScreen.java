package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Add Class Description
 */

public abstract class GameScreen implements Screen {

    protected Abstracto game;
    protected Assets assets;
    protected Stage stage;

    public GameScreen(Abstracto game) {
        this.game = game;
        assets = game.getAssets();
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(GameConstants.WIDTH, GameConstants.HEIGHT), game.getBatch());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
