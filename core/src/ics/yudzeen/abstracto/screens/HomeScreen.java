package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Add Class Description
 */

public class HomeScreen extends GameScreen {

    public static final String TAG = HomeScreen.class.getName();

    public HomeScreen(Abstracto game) {
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

        // My profile button
        TextButton myProfileButton = ButtonFactory.createTextButton("MY PROFILE");
        myProfileButton.setPosition(GameConstants.WIDTH/2 + 50, GameConstants.HEIGHT/2 + 50);
        myProfileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: my profile
            }
        });
        stage.addActor(myProfileButton);

        // Achievements button
        TextButton achievementsButton = ButtonFactory.createTextButton("ACHIEVEMENTS");
        achievementsButton.setPosition(myProfileButton.getX() + myProfileButton.getWidth()/2 - achievementsButton.getWidth()/2,
                myProfileButton.getY() - myProfileButton.getHeight() - 10);
        achievementsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: achievements
            }
        });
        stage.addActor(achievementsButton);

        ImageButton backButton = ButtonFactory.createImageButton(assets.buttons.back_arrow);
        backButton.setPosition(20, GameConstants.HEIGHT - backButton.getHeight() - 20);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(backButton);
    }

    private void buildTitle() {
        Label label = new Label("Home", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        label.setPosition(GameConstants.WIDTH/2 - label.getWidth()/2, GameConstants.HEIGHT - 50);
        stage.addActor(label);
    }
}
