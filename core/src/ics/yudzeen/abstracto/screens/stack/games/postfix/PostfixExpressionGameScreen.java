package ics.yudzeen.abstracto.screens.stack.games.postfix;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.games.ArcadeMapScreen;

/**
 * Screen of postfix expression game
 */

public class PostfixExpressionGameScreen extends AbstractoScreen {

    private GameController gameController;
    GameRenderer gameRenderer;

    public PostfixExpressionGameScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        gameController = new GameController(this);
        gameRenderer = new GameRenderer(this, gameController);
    }

    @Override
    public void render(float delta) {
        gameController.update(delta);
        gameRenderer.update(delta);
        super.render(delta);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        gameRenderer.buildStage();
    }

    public Abstracto getGame() {
        return game;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new ArcadeMapScreen(game));
    }
}
