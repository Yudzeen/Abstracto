package ics.yudzeen.abstracto.screens.stack;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.screens.stack.postfix.PostfixExpressionGameScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of stack map
 */

public class StackMapScreen extends AbstractoScreen {

    public StackMapScreen(Abstracto game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildBackground();
        buildLocation();
        buildButtons();
    }

    /**
     * Add background
     */
    private void buildBackground() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        pixmap.fill();
        Image background = new Image(new Texture(pixmap));
        background.setPosition(0,0);
        stage.addActor(background);
        pixmap.dispose();
    }

    private void buildLocation() {
        Image location = new Image(assets.images.location_stack);
        location.setPosition(0, GameConstants.HEIGHT - location.getHeight());
        stage.addActor(location);
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

        // Guru button
        final ImageButton guruButton = ButtonFactory.createImageButton(assets.buttons.guru);
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
                // temporary
                game.setScreen(new PostfixExpressionGameScreen(game));
            }
        });
        stage.addActor(townPlazaButton);

        // Simulator button
        ImageButton simulatorButton = ButtonFactory.createImageButton(assets.buttons.simulator);
        simulatorButton.setPosition(GameConstants.WIDTH/2 + 10, GameConstants.HEIGHT/2 + 80);
        simulatorButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ics.yudzeen.abstracto.screens.stack.simulator.StackSimulatorScreen(game));
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

    @Override
    protected void backKeyPressed() {
        game.setScreen(new WorldMapScreen(game));
    }
}
