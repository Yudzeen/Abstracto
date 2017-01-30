package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

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
        buildTitle();
        buildButtons();
    }

    private void buildTitle() {
        Image title = new Image(assets.images.title);
        title.setPosition(GameConstants.WIDTH/2 - title.getWidth()/2, GameConstants.HEIGHT - 150);
        stage.addActor(title);
    }

    private void buildButtons() {
        ImageButton startButton = ButtonFactory.createImageButton(assets.buttons.start);
        startButton.setPosition(GameConstants.WIDTH/2 - startButton.getWidth()/2, GameConstants.HEIGHT/2 - startButton.getHeight()/2 - 50);
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.debug(TAG, "Start button clicked.");
                game.setScreen(new HomeScreen(game));
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
}
