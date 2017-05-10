package ics.yudzeen.abstracto.screens.queue.duel;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;

/**
 * Queue duel screen
 */

class DuelScreen extends AbstractoScreen {

    static final String TAG = DuelScreen.class.getName();

    GameController gameController;
    GameRenderer gameRenderer;

    public DuelScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        gameController = new GameController(this);
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
        gameRenderer.render();
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new QueueMapScreen(game));
    }

    public Abstracto getGame() {
        return game;
    }


}
