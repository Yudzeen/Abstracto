package ics.yudzeen.abstracto.screens.stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.GameScreen;
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Add Class Description
 */

public class StackMapScreen extends GameScreen {

    public StackMapScreen(Abstracto game) {
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
        Label titleLabel = new Label("Stackville", new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        titleLabel.setPosition(GameConstants.WIDTH/2 - titleLabel.getWidth()/2, GameConstants.HEIGHT - 50);
        stage.addActor(titleLabel);
    }

    private void buildButtons() {
        // World map button
        ImageButton worldMapButton = ButtonFactory.createImageButton(assets.buttons.map);
        worldMapButton.setPosition(GameConstants.WIDTH - worldMapButton.getWidth() - 10,
                GameConstants.HEIGHT - worldMapButton.getHeight() - 10);
        worldMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new WorldMapScreen(game));
            }
        });
        stage.addActor(worldMapButton);

        final TextButton guruButton = ButtonFactory.createTextButton("Stack Guru");
        guruButton.setPosition(200, GameConstants.HEIGHT - 200);
        stage.addActor(guruButton);

        TextButton simulatorButton = ButtonFactory.createTextButton("Stack Simulator");
        simulatorButton.setPosition(200, guruButton.getY() - guruButton.getHeight() - 20);
        simulatorButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new StackSimulatorScreen(game));
            }
        });
        stage.addActor(simulatorButton);

        TextButton applicationButton = ButtonFactory.createTextButton("Stack Applications");
        applicationButton.setPosition(200, simulatorButton.getY() - simulatorButton.getHeight() - 20);
        stage.addActor(applicationButton);
    }

}
