package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Abstract class of screens
 */

public abstract class AbstractoScreen implements Screen {

    protected Abstracto game;
    protected Assets assets;
    protected Stage stage;

    public AbstractoScreen(Abstracto game) {
        this.game = game;
        assets = game.getAssets();
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(GameConstants.WIDTH, GameConstants.HEIGHT), game.getBatch()) {
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.BACK) {
                    backKeyPressed();
                }
                return super.keyDown(keyCode);
            }
        };
        Gdx.input.setInputProcessor(stage);
        buildStage();
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
        buildStage();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    protected void buildStage() {
        stage.clear();
    }

    protected void backKeyPressed() {
        game.setScreen(new TitleScreen(game));
    }
}
