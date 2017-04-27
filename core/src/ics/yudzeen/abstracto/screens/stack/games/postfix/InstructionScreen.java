package ics.yudzeen.abstracto.screens.stack.games.postfix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.games.ArcadeMapScreen;
import ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.BalancingSymbolsGameScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Postfix instruction screen
 */

public class InstructionScreen extends AbstractoScreen {

    public static final String TAG = InstructionScreen.class.getName();

    private Image backgroundImage;
    private Label instructionsLabel;

    private Label bodyLabel;
    private TextButton startButton;

    public InstructionScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initInstructionsLabel();
        initBodyLabel();
        initStartButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        stage.addActor(backgroundImage);
        stage.addActor(instructionsLabel);
        stage.addActor(bodyLabel);
        stage.addActor(startButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new ArcadeMapScreen(game));
    }

    private void initInstructionsLabel() {
        instructionsLabel = LabelFactory.createLabel("INSTRUCTIONS", assets.fonts.chalk_40, Color.WHITE);
        instructionsLabel.setPosition(GameConstants.WIDTH/2 - instructionsLabel.getWidth()/2,
                GameConstants.HEIGHT - instructionsLabel.getHeight() - 30);
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_blackboard);
    }

    private void initBodyLabel() {
        String text = "- Solve the postfix expression by pushing\n  or popping current node\n\n" +
                "- Avoid mistakes and finish before the \n  time runs out";
        bodyLabel = LabelFactory.createLabel(text, assets.fonts.chalk_20, Color.WHITE);
        bodyLabel.setPosition(150, instructionsLabel.getY() - bodyLabel.getHeight() - 20);
    }

    private void initStartButton() {
        startButton = ButtonFactory.createTextButton("START",20,20,new Color(14/255.0f, 69/255.0f, 31/255.0f, 1),Color.WHITE,assets.fonts.chalk_30);
        startButton.setPosition(GameConstants.WIDTH - startButton.getWidth() - 40,
                50);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PostfixExpressionGameScreen(game));
            }
        });
    }

}
