package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Add Class Description
 */

public class MenuScreen extends GameScreen {

    public static final String TAG = MenuScreen.class.getName();

    public MenuScreen(Abstracto game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        buildStage();
    }

    private void buildStage() {
        buildTitle();
        buildButtons();
    }

    private void buildTitle() {
        Label label = new Label("Abstracto", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        label.setPosition(GameConstants.WIDTH/2 - label.getWidth()/2, GameConstants.HEIGHT - 50);
        stage.addActor(label);
    }

    private void buildButtons() {
        TextButton startButton = ButtonFactory.createTextButton("START", 200, 80);
        startButton.setPosition(GameConstants.WIDTH - startButton.getWidth() - 100, 200);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.debug(TAG, "Start button clicked.");
                game.setScreen(new HomeScreen(game));
            }
        });
        stage.addActor(startButton);

        TextButton aboutButton = ButtonFactory.createTextButton("ABOUT");
        aboutButton.setPosition(startButton.getX() + startButton.getWidth()/2 - aboutButton.getWidth()/2, startButton.getY() - startButton.getHeight() - 10);
        aboutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: about
            }
        });
        stage.addActor(aboutButton);
    }
}
