package ics.yudzeen.abstracto.screens.stack.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.InstructionScreen;
import ics.yudzeen.abstracto.screens.stack.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Screen for applications
 */

public class ArcadeMapScreen extends AbstractoScreen {

    public static final String TAG = ArcadeMapScreen.class.getName();

    private static Color LABEL_COLOR = new Color(66/255.0f, 76/255.0f, 59/255.0f, 1.0f);

    private Image backgroundImage;
    private Label titleLabel;
    private Image personImage;

    private ImageButton postfixExpressionButton;
    private ImageButton balancingSymbolsButton;

    public ArcadeMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleLabel();
        initPersonImage();
        initBalancingSymbolsButton();
        initPostfixExpressionButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(personImage);

        stage.addActor(balancingSymbolsButton);
        stage.addActor(postfixExpressionButton);
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        //pixmap.setColor(0,0,0,1);
        pixmap.setColor(StackMapScreen.CELADON);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initTitleLabel() {
        titleLabel = LabelFactory.createLabel("ARCADE", assets.fonts.verdana_40, LABEL_COLOR);
        titleLabel.setPosition(GameConstants.WIDTH/2 - titleLabel.getWidth()/2,
                GameConstants.HEIGHT - titleLabel.getHeight() - 30);
    }

    private void initPersonImage() {
        GamePreferences gamePreferences = game.getGamePreferences();
        gamePreferences.load();
        personImage = new Image(gamePreferences.character.equals("MALE") ? assets.images.female : assets.images.male);
        personImage.setPosition(GameConstants.WIDTH/4 - personImage.getWidth()/2,
                GameConstants.HEIGHT/2 - personImage.getHeight()/2);
    }
    private void initBalancingSymbolsButton() {
        balancingSymbolsButton = ButtonFactory.createImageButton(assets.buttons.balancing_game);
        balancingSymbolsButton.setPosition(GameConstants.WIDTH*3/4 - balancingSymbolsButton.getWidth()/2 - 80,
                GameConstants.HEIGHT - balancingSymbolsButton.getHeight() - 150);
        balancingSymbolsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InstructionScreen(game));
            }
        });
    }

    private void initPostfixExpressionButton() {
        postfixExpressionButton = ButtonFactory.createImageButton(assets.buttons.postfix_game);
        postfixExpressionButton.setPosition(balancingSymbolsButton.getX(),
                balancingSymbolsButton.getY() - 60 - postfixExpressionButton.getHeight());
        postfixExpressionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ics.yudzeen.abstracto.screens.stack.games.postfix.InstructionScreen(game));
            }
        });
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }

}
