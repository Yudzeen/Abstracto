package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.ui.TextFieldFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Enter name screen
 */

public class EnterNameScreen extends AbstractoScreen {

    static final String TAG = EnterNameScreen.class.getName();

    private Image backgroundImage;

    private Label enterNameLabel;
    private TextField nameField;
    private ImageButton enterButton;

    private Image characterImage;

    public EnterNameScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initCharacterImage();
        initEnterNameLabel();
        initNameField();
        initEnterButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(characterImage);
        stage.addActor(nameField);
        stage.addActor(enterNameLabel);
        stage.addActor(enterButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new ChooseCharacterScreen(game));
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

    private void initCharacterImage() {
        TextureAtlas.AtlasRegion texture;
        if(game.getGamePreferences().character.equals("MALE")) {
            texture = assets.images.male;
        }
        else {
            texture = assets.images.female;
        }
        characterImage = new Image(texture);
        characterImage.setPosition(GameConstants.WIDTH/4 - characterImage.getWidth()/2,
                GameConstants.HEIGHT/2 - characterImage.getHeight()/2);
    }

    private void initNameField() {
        nameField = TextFieldFactory.createTextField("", assets.fonts.verdana_30, Color.BLACK);
        nameField.setPosition(enterNameLabel.getX() + 50, enterNameLabel.getY() - nameField.getHeight() - 20);
    }

    private void initEnterNameLabel() {
        enterNameLabel = LabelFactory.createLabel("Enter your name: ", assets.fonts.verdana_30);
        enterNameLabel.setPosition(GameConstants.WIDTH/2 + 20, GameConstants.HEIGHT - enterNameLabel.getHeight() - 20);
    }

    private void initEnterButton() {
        enterButton = ButtonFactory.createImageButton(assets.buttons.play);
        enterButton.setPosition(GameConstants.WIDTH - enterButton.getWidth() - 20,
                20);
        enterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GamePreferences gamePreferences = getGame().getGamePreferences();
                gamePreferences.name = nameField.getText();
                gamePreferences.save();
                Gdx.app.debug(TAG, "Name entered: " + nameField.getText());
                game.setScreen(new IntroductionScreen(game));
            }
        });
    }
}
