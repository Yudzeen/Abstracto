package ics.yudzeen.abstracto.screens.stack.postfix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Created by Yujin on 02/04/2017.
 */

public class GameOverScreen extends AbstractoScreen {

    public static final String TAG = GameOverScreen.class.getName();

    private Label gameOverLabel;

    public GameOverScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initGameOverLabel();
    }

    private void initGameOverLabel() {
        gameOverLabel = LabelFactory.createLabel("GAME OVER!", assets.fonts.defaultVeryBig, Color.WHITE);
        gameOverLabel.setPosition(GameConstants.WIDTH/2-gameOverLabel.getWidth()/2, GameConstants.HEIGHT/2-gameOverLabel.getHeight()/2);
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        stage.addActor(gameOverLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }
}
