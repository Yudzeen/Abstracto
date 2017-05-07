package ics.yudzeen.abstracto.screens.stack.school.applications;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * School Applications Screen
 */

public class ApplicationsScreen extends AbstractoScreen {

    public static final String TAG = ApplicationsScreen.class.getName();

    private ImageButton balancingButton;
    private ImageButton postfixButton;

    public ApplicationsScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBalancingButton();
        initPostfixButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(balancingButton);
        stage.addActor(postfixButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new SchoolScreen(game));
    }

    private void initBalancingButton() {
        balancingButton = ButtonFactory.createImageButton(assets.buttons.balancing_apps);
        balancingButton.setPosition(200, GameConstants.HEIGHT - balancingButton.getHeight() - 200);
        balancingButton.addListener(new ClickListener() {
            // balancing symbol application
        });
    }

    private void initPostfixButton() {
        postfixButton = ButtonFactory.createImageButton(assets.buttons.postfix_apps);
        postfixButton.setPosition(balancingButton.getX(), balancingButton.getY() - postfixButton.getHeight() - 10);
        postfixButton.addListener(new ClickListener() {
            // postfix app
        });
    }
}
