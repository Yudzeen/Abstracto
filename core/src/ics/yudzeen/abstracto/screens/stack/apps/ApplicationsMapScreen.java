package ics.yudzeen.abstracto.screens.stack.apps;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.apps.balancingsymbols.BalancingSymbolsGameScreen;
import ics.yudzeen.abstracto.screens.stack.apps.postfix.PostfixExpressionGameScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;

/**
 * Screen for applications
 */

public class ApplicationsMapScreen extends AbstractoScreen {

    public static final String TAG = ApplicationsMapScreen.class.getName();

    private TextButton postfixExpressionButton;
    private TextButton balancingSymbolsButton;

    public ApplicationsMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initPostfixExpressionButton();
        initBalancingSymbolsButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(postfixExpressionButton);
        stage.addActor(balancingSymbolsButton);
    }

    private void initPostfixExpressionButton() {
        postfixExpressionButton = ButtonFactory.createTextButton("Postfix Expression Evaluation");
        postfixExpressionButton.setPosition(200, 400);
        postfixExpressionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PostfixExpressionGameScreen(game));
            }
        });
    }

    private void initBalancingSymbolsButton() {
        balancingSymbolsButton = ButtonFactory.createTextButton("Balancing Symbols");
        balancingSymbolsButton.setPosition(200, 200);
        balancingSymbolsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new BalancingSymbolsGameScreen(game));
            }
        });
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }
}
