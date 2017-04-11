package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of main menu
 */

public class TitleScreen extends AbstractoScreen {

    public static final String TAG = TitleScreen.class.getName();

    public TitleScreen(Abstracto game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildBackground();
        buildTitle();
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

    /**
     * Add title image
     */
    private void buildTitle() {
        Image title = new Image(assets.images.title_main);
        title.setPosition(GameConstants.WIDTH/2 - title.getWidth()/2, GameConstants.HEIGHT - 150);
        stage.addActor(title);
    }

    /**
     * Add buttons
     */
    private void buildButtons() {
        ImageButton startButton = ButtonFactory.createImageButton(assets.buttons.start);
        startButton.setPosition(GameConstants.WIDTH/2 - startButton.getWidth()/2, GameConstants.HEIGHT/2 - startButton.getHeight()/2 - 50);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.debug(TAG, "Start button clicked.");
                //game.setScreen(new HomeScreen(game));
                game.setScreen(new WorldMapScreen(game));
            }
        });
        stage.addActor(startButton);

        ImageButton aboutButton = ButtonFactory.createImageButton(assets.buttons.about);
        aboutButton.setPosition(GameConstants.WIDTH/2 - aboutButton.getWidth()/2, startButton.getY() - 80);
        aboutButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: about
            }
        });
        stage.addActor(aboutButton);
    }

    @Override
    protected void backKeyPressed() {
        Gdx.app.exit();
    }
}
