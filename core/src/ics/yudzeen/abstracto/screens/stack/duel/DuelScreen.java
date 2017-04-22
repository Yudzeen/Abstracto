package ics.yudzeen.abstracto.screens.stack.duel;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;

/**
 * Screen for duel against master
 */

public class DuelScreen extends AbstractoScreen {

    public static final String TAG = DuelScreen.class.getName();

    private GameController gameController;
    private GameRenderer gameRenderer;

    public DuelScreen(Abstracto game) {
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
        gameRenderer.render();
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }

    public Abstracto getGame() {
        return game;
    }
}
