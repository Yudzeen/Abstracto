package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Choose character screen
 */

public class ChooseCharacterScreen extends AbstractoScreen {

    static final String TAG = ChooseCharacterScreen.class.getName();

    private Image backgroundImage;
    private Label chooseLabel;

    private ImageButton maleButton;
    private ImageButton femaleButton;

    public ChooseCharacterScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initChooseLabel();
        initMaleButton();
        initFemaleButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(chooseLabel);
        stage.addActor(maleButton);
        stage.addActor(femaleButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new TitleScreen(game));
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        //pixmap.setColor(0,0,0,1);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initChooseLabel() {
        chooseLabel = LabelFactory.createLabel("Are you a boy or a girl? ", assets.fonts.verdana_40, Color.WHITE);
        chooseLabel.setPosition(20, GameConstants.HEIGHT - chooseLabel.getHeight() - 20);
    }

    private void initMaleButton() {
        maleButton = ButtonFactory.createImageButton(assets.images.male);
        maleButton.setPosition(GameConstants.WIDTH/4 - maleButton.getWidth()/2, 30);
        maleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GamePreferences gamePreferences = game.getGamePreferences();
                gamePreferences.character = "MALE";
                gamePreferences.save();
                game.setScreen(new EnterNameScreen(game));
            }
        });
    }

    private void initFemaleButton() {
        femaleButton = ButtonFactory.createImageButton(assets.images.female);
        femaleButton.setPosition(GameConstants.WIDTH*3/4 - maleButton.getWidth()/2, 30);
        femaleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GamePreferences gamePreferences = game.getGamePreferences();
                gamePreferences.character = "FEMALE";
                gamePreferences.save();
                game.setScreen(new EnterNameScreen(game));
            }
        });
    }
}
