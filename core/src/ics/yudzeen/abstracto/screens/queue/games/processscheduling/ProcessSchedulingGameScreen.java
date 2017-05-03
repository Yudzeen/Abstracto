package ics.yudzeen.abstracto.screens.queue.games.processscheduling;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.games.ArcadeMapScreen;

/**
 * Process Scheduling Game Screen
 */

public class ProcessSchedulingGameScreen extends AbstractoScreen {

    public static final String TAG = ProcessSchedulingGameScreen.class.getName();

    GameController gameController;
    GameRenderer gameRenderer;

    public ProcessSchedulingGameScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        gameController = new GameController();
        gameRenderer = new GameRenderer(this, gameController);
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        gameRenderer.buildStage(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameController.update(delta);
        gameRenderer.render(stage);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new ArcadeMapScreen(game));
    }
}
