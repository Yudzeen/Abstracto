package ics.yudzeen.abstracto.screens.stack.games;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.BalancingSymbolsGameScreen;
import ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.InstructionScreen;
import ics.yudzeen.abstracto.screens.stack.games.postfix.PostfixExpressionGameScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen for applications
 */

public class ArcadeMapScreen extends AbstractoScreen {

    public static final String TAG = ArcadeMapScreen.class.getName();

    private ImageButton postfixExpressionButton;
    private ImageButton balancingSymbolsButton;

    public ArcadeMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBalancingSymbolsButton();
        initPostfixExpressionButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(balancingSymbolsButton);
        stage.addActor(postfixExpressionButton);
    }

    private void initBalancingSymbolsButton() {
        balancingSymbolsButton = ButtonFactory.createImageButton(assets.buttons.balancing_game);
        balancingSymbolsButton.setPosition(200, GameConstants.HEIGHT - balancingSymbolsButton.getHeight() - 200);
        balancingSymbolsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InstructionScreen(game));
            }
        });
    }

    private void initPostfixExpressionButton() {
        postfixExpressionButton = ButtonFactory.createImageButton(assets.buttons.postfix_game);
        postfixExpressionButton.setPosition(200, balancingSymbolsButton.getY() - postfixExpressionButton.getHeight() - 10);
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
