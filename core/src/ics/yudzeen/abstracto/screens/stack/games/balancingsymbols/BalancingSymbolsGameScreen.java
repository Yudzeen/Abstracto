package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.games.ArcadeMapScreen;

/**
 * Screen for balancing symbols
 */

public class BalancingSymbolsGameScreen extends AbstractoScreen {

    public static final String TAG = BalancingSymbolsGameScreen.class.getName();

    private GameController gameController;
    private GameRenderer gameRenderer;

    public BalancingSymbolsGameScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        gameController = new GameController();
        gameRenderer = new GameRenderer(this, gameController);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        gameController.update(delta);
        gameRenderer.render(stage);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        gameRenderer.buildStage(stage);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new ArcadeMapScreen(game));
    }

    public Abstracto getGame() {
        return game;
    }
}
