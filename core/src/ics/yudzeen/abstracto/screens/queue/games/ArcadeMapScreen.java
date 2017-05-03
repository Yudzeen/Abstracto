package ics.yudzeen.abstracto.screens.queue.games;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.games.customercashier.CustomerCashierScreen;
import ics.yudzeen.abstracto.screens.queue.games.processscheduling.ProcessSchedulingGameScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen for applications
 */

public class ArcadeMapScreen extends AbstractoScreen {

    public static final String TAG = ArcadeMapScreen.class.getName();

    private ImageButton cpuSchedulingButton;
    private ImageButton customerCashierButton;

    public ArcadeMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initCPUSchedulingButton();
        initCustomerCashierButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(cpuSchedulingButton);
        stage.addActor(customerCashierButton);
    }

    private void initCPUSchedulingButton() {
        cpuSchedulingButton = ButtonFactory.createImageButton(assets.buttons.process_game);
        cpuSchedulingButton.setPosition(200, GameConstants.HEIGHT - cpuSchedulingButton.getHeight() - 200);
        cpuSchedulingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ProcessSchedulingGameScreen(game));
            }
        });
    }

    private void initCustomerCashierButton() {
        customerCashierButton = ButtonFactory.createImageButton(assets.buttons.cashier_game);
        customerCashierButton.setPosition(200, cpuSchedulingButton.getY() - customerCashierButton.getHeight() - 10);
        customerCashierButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CustomerCashierScreen(game));
            }
        });
    }



    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }
}
