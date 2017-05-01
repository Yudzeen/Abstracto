package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Renderer of the game
 */

class GameRenderer {

    static final String TAG = GameRenderer.class.getName();

    private BalancingSymbolsGameScreen gameScreen;
    Assets assets;
    GameController gameController;

    Image backgroundImage;
    Image stopwatchImage;
    Label timerLabel;
    Image[] heartImages;
    Label expressionsLeftLabel;

    ImageButton pushButton;
    ImageButton popButton;

    Image squareImage;
    Image lineImage;

    ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.StackContainer stackContainer;
    List<ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode> nodesQueue;

    ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode poppingNode;

    private Image blackImage;

    private Label readySetGoText;

    public GameRenderer(BalancingSymbolsGameScreen gameScreen, GameController gameController) {
        this.gameScreen = gameScreen;
        this.gameController = gameController;
        init();
    }

    private void init() {
        assets = gameScreen.getAssets();
        initBackgroundImage();
        initTimer();
        initLives();
        initExpressionsLeftLabel();
        initStackContainerImage();
        initPushButton();
        initPopButton();
        initSquareImage();
        initLineImage();
        initNodesQueue();
        initReadySetGoText();
        initBlackImage();
    }

    public void buildStage(Stage stage) {
        stage.addActor(backgroundImage);
        stage.addActor(stopwatchImage);
        stage.addActor(timerLabel);

        for (Image heartImage: heartImages) {
            stage.addActor(heartImage);
        }

        //stage.addActor(expressionsLeftLabel);
        stage.addActor(stackContainer);
        stage.addActor(pushButton);
        stage.addActor(popButton);
        stage.addActor(readySetGoText);

        stage.addActor(squareImage);
        stage.addActor(lineImage);

        for (ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode node: nodesQueue) {
            node.setVisible(false);
            stage.addActor(node);
        }
        blackImage.setVisible(false);
        stage.addActor(blackImage);
    }

    public void render(Stage stage) {
        boolean gameStarted = gameController.gameStarted;

        if(gameStarted) {
            lightUpBackground();
            readySetGoText.remove();
            if (!gameController.gameOver) {
                renderTimer();
                renderLives();
                renderNodesQueue();
            }
            else {
                disablePushAndPopButtons(true);
                final Abstracto game = gameScreen.getGame();
                stage.getRoot().getColor().a = 1;
                SequenceAction sequenceAction = new SequenceAction();
                float delay = gameController.gameWin ? 5.0f : 1.5f;
                sequenceAction.addAction(delay(delay));
                sequenceAction.addAction(fadeOut(2.0f));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameOverScreen(game, gameController.gameWin));
                    }
                }));
                stage.addAction(sequenceAction);
            }
        }
        else {
            dimBackground();
            renderReadySetGoText();
        }
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initBlackImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(0,0,0,0.5f));
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.draw(pixmap,0,0);
        blackImage = new Image(texture);
        blackImage.setColor(0,0,0,0.5f);
        pixmap.dispose();
    }

    private void initTimer() {
        stopwatchImage = new Image(assets.games.stopwatch);
        stopwatchImage.setPosition(0, GameConstants.HEIGHT - stopwatchImage.getHeight() - 10);

        timerLabel = LabelFactory.createLabel(Integer.toString(GameController.TIME_LIMIT), assets.fonts.defaultVeryBig);
        timerLabel.setPosition(65, GameConstants.HEIGHT - timerLabel.getHeight() - 10);
    }

    private void initLives() {
        heartImages = new Image[GameController.MAX_LIVES];

        for (int i = 0; i < heartImages.length; i++) {
            heartImages[i] = new Image(assets.games.heart);
            heartImages[i].setPosition(10+i*40, GameConstants.HEIGHT - heartImages[i].getHeight() - 75);
            heartImages[i].setColor(Color.WHITE);
        }
    }

    private void initExpressionsLeftLabel() {
        expressionsLeftLabel = LabelFactory.createLabel("Expressions left: " + GameController.EXPRESSION_COUNT, assets.fonts.defaultBig);
        expressionsLeftLabel.setPosition(GameConstants.WIDTH - expressionsLeftLabel.getWidth() - 10, 5);
    }

    private void initStackContainerImage() {
        stackContainer = new ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.StackContainer(this);
        stackContainer.setPosition(200, 10);
    }

    private void initPushButton() {
        pushButton = ButtonFactory.createImageButton(assets.games.push);
        pushButton.setPosition(GameConstants.WIDTH/2 - 20, 100);
        pushButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPushPressed();
            }
        });
    }

    private void initPopButton() {
        popButton = ButtonFactory.createImageButton(assets.games.pop);
        popButton.setPosition(GameConstants.WIDTH/2 + 170, 100);
        popButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPopPressed();
            }
        });
    }

    private void initSquareImage() {
        Pixmap pixmap = new Pixmap(80,80, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAROON);
        for (int i = 77, j=0; i <= 80; i++, j++) {
            pixmap.drawRectangle(j,j,i-j,i-j);
        }
        squareImage = new Image(new Texture(pixmap));
        squareImage.setPosition(GameConstants.WIDTH/2-squareImage.getWidth()/2, GameConstants.HEIGHT-squareImage.getHeight()-57);
    }

    private void initLineImage() {
        Pixmap pixmap = new Pixmap(400, 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        lineImage = new Image(new Texture(pixmap));
        lineImage.setPosition(GameConstants.WIDTH/2 + 25, GameConstants.HEIGHT-275);
    }

    private void initNodesQueue() {
        nodesQueue = new ArrayList<>();
        for (String s : gameController.getCurrentExpression()) {
            ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode node = new ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode(this, s);
            nodesQueue.add(node);
        }
    }

    private void initReadySetGoText() {
        readySetGoText = LabelFactory.createLabel("READY", assets.fonts.defaultLarge, Color.RED);
        readySetGoText.setPosition(GameConstants.WIDTH/2-readySetGoText.getWidth()/2, GameConstants.HEIGHT/2-readySetGoText.getHeight()/2);
    }

    private void renderTimer() {
        float time = gameController.timer;
        if(time < 10 && time > 0) {
            timerLabel.getStyle().fontColor = Color.RED;
            timerLabel.setText(String.format(Locale.US, "%.1f", time));
        }
        else {
            timerLabel.setText(Integer.toString((int) time));
        }
    }

    private void renderLives() {
        for (int i = 0; i < heartImages.length; i++) {
            if(gameController.lives <= i) {
                heartImages[i].setColor(Color.GRAY);
            }
            else {
                heartImages[i].setColor(Color.WHITE);
            }
        }
    }

    private void renderNodesQueue() {
        for (int i = 0; i < nodesQueue.size(); i++){
            ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode node = nodesQueue.get(i);
            float nodeWidth = node.getWidth();
            float nodeHeight = node.getHeight();
            node.setVisible(true);
            if(i==0) {
                node.setCurrentNode(true);
                node.setPosition(GameConstants.WIDTH/2 - nodeWidth/2, GameConstants.HEIGHT - nodeHeight - 70);
            }
            else {
                node.setPosition(GameConstants.WIDTH/2 - nodeWidth/2 + (5+nodeWidth)*(i-1) + 50, GameConstants.HEIGHT/2 - nodeHeight/2);
            }
        }
    }

    private void renderReadySetGoText() {
        readySetGoText.toFront();
        readySetGoText.setText(gameController.readySetGoText);
        float width = readySetGoText.getGlyphLayout().width;
        float height = readySetGoText.getGlyphLayout().height;
        readySetGoText.setPosition(GameConstants.WIDTH/2 - width/2, GameConstants.HEIGHT/2 - height/2);
        readySetGoText.setWidth(width);
        readySetGoText.setHeight(height);
    }

    private void onPushPressed() {
        if (gameController.onPushPressed()) {
            disablePushAndPopButtons(true);
            ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode node = nodesQueue.remove(0);
            node.setCurrentNode(false);
            stackContainer.push(node);
        }
        else {
            renderLives();
        }
    }

    private void onPopPressed() {
        if (gameController.onPopPressed()) {
            disablePushAndPopButtons(true);
            poppingNode = stackContainer.pop();
        }
        else {
            renderLives();
        }
    }

    void removeMergingNodes() {
        poppingNode.remove();
        ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode node = nodesQueue.remove(0);
        node.setCurrentNode(false);
        node.remove();
    }

    void disablePushAndPopButtons(boolean disable) {
        if (disable) {
            pushButton.setTouchable(Touchable.disabled);
            popButton.setTouchable(Touchable.disabled);
            pushButton.setDisabled(true);
            popButton.setDisabled(true);
        }
        else {
            pushButton.setTouchable(Touchable.enabled);
            popButton.setTouchable(Touchable.enabled);
            pushButton.setDisabled(false);
            popButton.setDisabled(false);
        }

    }

    ics.yudzeen.abstracto.screens.stack.games.balancingsymbols.GameNode getCurrentNode() {
        if (!nodesQueue.isEmpty())
            return nodesQueue.get(0);
        else
            return null;
    }

    private void dimBackground() {
        blackImage.setVisible(true);
    }

    private void lightUpBackground() {
        blackImage.setVisible(false);
    }

}
