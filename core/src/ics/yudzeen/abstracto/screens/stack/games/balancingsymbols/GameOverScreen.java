package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.games.ArcadeMapScreen;
import ics.yudzeen.abstracto.screens.stack.games.postfix.PostfixExpressionGameScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Game over screen for balancing symbols game
 */

class GameOverScreen extends AbstractoScreen {

    public static final String TAG = GameOverScreen.class.getName();

    private Image backgroundImage;
    private Image personImage;
    private Image chatBubble;
    private Label chatLabel;

    private Label gameOverLabel;

    private ImageButton retryButton;
    private Label retryLabel;

    private ImageButton exitButton;
    private Label exitLabel;

    private boolean gameWin;

    public GameOverScreen(Abstracto game, boolean gameWin) {
        super(game);
        this.gameWin = gameWin;
        init();
    }

    private void init() {
        initBackgroundImage();
        initPersonImage();
        initChatBubble();
        initChatLabel();
        initGameOverLabel();
        initRetryButton();
        initRetryLabel();
        initExitButton();
        initExitLabel();
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(66/255.0f, 76/255.0f, 59/255.0f, 1.0f));
        pixmap.fill();

        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initPersonImage() {
        GamePreferences gamePreferences = game.getGamePreferences();
        gamePreferences.load();
        personImage = new Image(gamePreferences.character.equals("MALE") ? assets.images.female : assets.images.male);
        personImage.setPosition(GameConstants.WIDTH/4 - personImage.getWidth()/2 - 40,
                GameConstants.HEIGHT/2 - personImage.getHeight()/2 - 40 - 60);
    }

    private void initChatBubble() {
        chatBubble = new Image(assets.images.chat_bubble);
        chatBubble.setPosition(GameConstants.WIDTH/4 + 30, GameConstants.HEIGHT/2 - 30 - 60);
    }

    private void initChatLabel() {
        String text = gameWin ? "Well played " + game.getGamePreferences().name + "!" : "That did not \nturn out so well.";
        chatLabel = LabelFactory.createLabel(text, assets.fonts.verdana_30);
        chatLabel.setPosition(250 + 325/2 - chatLabel.getWidth()/2, 210 + 180/2 - chatLabel.getHeight()/2);
    }

    private void initGameOverLabel() {
        String text = gameWin ? "YOU WIN!" : "GAME OVER!";
        gameOverLabel = LabelFactory.createLabel(text, assets.fonts.verdana_40, StackMapScreen.CELADON);
        gameOverLabel.setPosition(GameConstants.WIDTH/2-gameOverLabel.getWidth()/2,
                GameConstants.HEIGHT-gameOverLabel.getHeight()-20);
    }

    private void initRetryButton() {
        retryButton = ButtonFactory.createImageButton(assets.games.retry_icon);
        retryButton.setPosition(GameConstants.WIDTH/2-retryButton.getWidth()-30 + 90+50, 80);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onRetryButtonClicked();
            }
        });
    }

    private void initRetryLabel() {
        String text = gameWin ? "PLAY AGAIN" : "RETRY";
        retryLabel = LabelFactory.createLabel(text, assets.fonts.verdana_30, Color.WHITE);
        retryLabel.setPosition(retryButton.getX()+retryButton.getWidth()/2-retryLabel.getWidth()/2,
                retryButton.getY()-retryLabel.getHeight()-10);
    }

    private void initExitButton() {
        exitButton = ButtonFactory.createImageButton(assets.games.exit_icon);
        exitButton.setPosition(GameConstants.WIDTH/2+30+90+80, 80);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onExitButtonClicked();
            }
        });
    }

    private void initExitLabel() {
        exitLabel = LabelFactory.createLabel("EXIT", assets.fonts.verdana_30, Color.WHITE);
        exitLabel.setPosition(exitButton.getX()+(exitButton.getWidth()-exitLabel.getWidth())/2,
                exitButton.getY()-exitLabel.getHeight()-10);
    }

    private void onRetryButtonClicked() {
        game.setScreen(new PostfixExpressionGameScreen(game));
    }

    private void onExitButtonClicked() {
        game.setScreen(new ArcadeMapScreen(game));
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        stage.addActor(backgroundImage);
        stage.addActor(personImage);
        stage.addActor(chatBubble);
        stage.addActor(chatLabel);

        stage.addActor(gameOverLabel);

        stage.addActor(retryButton);
        stage.addActor(retryLabel);

        stage.addActor(exitButton);
        stage.addActor(exitLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new ArcadeMapScreen(game));
    }

}
