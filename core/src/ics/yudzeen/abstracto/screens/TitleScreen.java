package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of main menu
 */

public class TitleScreen extends AbstractoScreen {

    public static final String TAG = TitleScreen.class.getName();

    private Image backgroundImage;
    private Image titleImage;

    private ImageButton startButton;
    private ImageButton aboutButton;

    public TitleScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleImage();
        initStartButton();
        initAboutButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(titleImage);
        stage.addActor(startButton);
        stage.addActor(aboutButton);
    }

    @Override
    protected void backKeyPressed() {
        Gdx.app.exit();
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initTitleImage() {
        titleImage = new Image(assets.images.title_main);
        titleImage.setPosition(GameConstants.WIDTH/2 - titleImage.getWidth()/2, GameConstants.HEIGHT - 150);
    }

    private void initStartButton() {
        startButton = ButtonFactory.createImageButton(assets.buttons.start);
        startButton.setPosition(GameConstants.WIDTH/2 - startButton.getWidth()/2,
                GameConstants.HEIGHT/2 - startButton.getHeight()/2 - 50);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WorldMapScreen(game));
            }
        });
    }

    private void initAboutButton() {
        aboutButton = ButtonFactory.createImageButton(assets.buttons.about);
        aboutButton.setPosition(GameConstants.WIDTH/2 - aboutButton.getWidth()/2,
                startButton.getY() - aboutButton.getHeight() - 20);
        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 About screen
            }
        });
    }

}
