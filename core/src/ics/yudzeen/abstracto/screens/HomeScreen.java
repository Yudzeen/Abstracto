package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of home
 */

public class HomeScreen extends AbstractoScreen {

    public static final String TAG = HomeScreen.class.getName();

    public HomeScreen(Abstracto game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildLocation();
        buildCharacter();
        buildButtons();
    }

    private void buildLocation() {
        Image location = new Image(assets.images.location_home);
        location.setPosition(0, GameConstants.HEIGHT - location.getHeight());
        stage.addActor(location);
    }

    private void buildCharacter() {
        Image character = new Image(assets.images.character);
        character.setPosition(150, 0);
        stage.addActor(character);
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
        ImageButton profileButton = ButtonFactory.createImageButton(assets.buttons.profile);
        profileButton.setPosition(GameConstants.WIDTH/2 + 10, GameConstants.HEIGHT/2 + 30);
        profileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: my profile
            }
        });
        stage.addActor(profileButton);

        // Achievements button
        ImageButton achievementsButton = ButtonFactory.createImageButton(assets.buttons.achievements);
        achievementsButton.setPosition(profileButton.getX() + profileButton.getWidth()/2 - achievementsButton.getWidth()/2,
                profileButton.getY() - profileButton.getHeight() - 20);
        achievementsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: achievements
            }
        });
        stage.addActor(achievementsButton);
    }
}
