package ics.yudzeen.abstracto.screens.queue;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of the queue map
 */

public class QueueMapScreen extends AbstractoScreen {

    public QueueMapScreen(Abstracto game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildLocation();
        buildButtons();
    }

    /**
     * Add the location image to the stage
     */
    private void buildLocation() {
        Image location = new Image(assets.images.location_queue);
        location.setPosition(0, GameConstants.HEIGHT - location.getHeight());
        stage.addActor(location);
    }

    /**
     * Add buttons to the stage
     */
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

        // Guru button
        ImageButton guruButton = ButtonFactory.createImageButton(assets.buttons.guru);
        guruButton.setPosition(75, GameConstants.HEIGHT/2);
        guruButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: guru screen
            }
        });
        stage.addActor(guruButton);

        // Town plaza button
        ImageButton townPlazaButton = ButtonFactory.createImageButton(assets.buttons.townplaza);
        townPlazaButton.setPosition(250, 75);
        townPlazaButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: town plaza screen
            }
        });
        stage.addActor(townPlazaButton);

        // Simulator button
        ImageButton simulatorButton = ButtonFactory.createImageButton(assets.buttons.simulator);
        simulatorButton.setPosition(GameConstants.WIDTH/2 + 10, GameConstants.HEIGHT/2 + 80);
        simulatorButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: simulator screen
            }
        });
        stage.addActor(simulatorButton);

        // Monster fight button
        ImageButton monsterFightButton = ButtonFactory.createImageButton(assets.buttons.monsterfight);
        monsterFightButton.setPosition(GameConstants.WIDTH/2 + 200, GameConstants.HEIGHT/2 - 30);
        monsterFightButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: monster fight screen
            }
        });
        stage.addActor(monsterFightButton);
    }
}
