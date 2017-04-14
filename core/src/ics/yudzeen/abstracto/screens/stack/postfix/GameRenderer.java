package ics.yudzeen.abstracto.screens.stack.postfix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Renders the game
 */

class GameRenderer {

    public static final String TAG = GameRenderer.class.getName();

    private PostfixExpressionGameScreen gameScreen;
    private GameController gameController;
    private Assets assets;

    private Image backgroundImage;

    private Image stopwatchImage;
    private Label timerText;

    private Image[] heartImages;

    private StackContainer stackContainer;

    private ImageButton pushButton;
    private ImageButton popButton;

    private ImageButton pauseButton;

    private Label expressionsLeftText;

    private ShapeRenderer shapeRenderer;
    private Label readySetGoText;

    private Image blackImage;

    private List<GameNode> nodesQueue;

    private OrthographicCamera camera;

    private Image lineImage;
    private Image squareImage;

    private PoppedNodes poppedNodes;

    public GameRenderer(PostfixExpressionGameScreen gameScreen, GameController gameController) {
        this.gameScreen = gameScreen;
        this.gameController = gameController;
        assets = gameScreen.getAssets();
        shapeRenderer = new ShapeRenderer();
        nodesQueue = new ArrayList<GameNode>();
        camera = new OrthographicCamera(GameConstants.WIDTH, GameConstants.HEIGHT);
        shapeRenderer.setProjectionMatrix(camera.combined);
        init();
    }

    private void init() {
        initBackground();
        initTimer();
        initLives();
        initStack();
        initPop();
        initPush();
        //initExpressionsLeft();
        initPause();
        initReadySetGoText();
        initBlackImage();
        initNodesQueue();
        initExtras();
        initPoppedNodes();
    }

    private void initBackground() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.draw(pixmap,0,0);
        backgroundImage = new Image(texture);
        pixmap.dispose();
    }

    private void initTimer() {
        stopwatchImage = new Image(assets.games.stopwatch);
        stopwatchImage.setPosition(0, GameConstants.HEIGHT - stopwatchImage.getHeight() - 10);

        timerText = LabelFactory.createLabel(Integer.toString((int) gameController.getTime()), assets.fonts.defaultVeryBig);
        timerText.setPosition(65, GameConstants.HEIGHT - timerText.getHeight() - 10);
    }

    private void initLives() {
        heartImages = new Image[GameController.MAX_LIVES];

        for (int i = 0; i < heartImages.length; i++) {
            heartImages[i] = new Image(assets.games.heart);
            heartImages[i].setPosition(10+i*40, GameConstants.HEIGHT - heartImages[i].getHeight() - 75);
            heartImages[i].setColor(Color.WHITE);
        }
    }

    private void initStack() {
        stackContainer = new StackContainer(gameScreen);
        stackContainer.setPosition(200, 10);
    }

    private void initPush() {
        pushButton = ButtonFactory.createImageButton(assets.games.push);
        pushButton.setPosition(GameConstants.WIDTH/2 - 20, 100);
        pushButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPushPressed();
            }
        });
    }

    private void onPushPressed() {
        boolean pushSuccessed = gameController.onPushPressed();
        if(pushSuccessed) {
            GameNode node = nodesQueue.remove(0);
            String nodeText = node.getText();
            // soft fix for bug when trying to push while merging
            if(nodeText.equals("+") || nodeText.equals("-") || nodeText.equals("*") || nodeText.equals("/") && nodesQueue.size() != 1) {
                GameNode newNode = new GameNode(gameScreen, gameController.peekStack());
                newNode.setPosition(node.getX(), node.getY());
                node.remove();
                gameScreen.getStage().addActor(newNode);
                poppedNodes.getPoppedNodes()[0].remove();
                poppedNodes.getPoppedNodes()[1].remove();
                newNode.setPushing(true);
                stackContainer.push(newNode);
            }
            else {
                node.toFront();
                node.setPushing(true);
                stackContainer.push(node);
            }

        }
    }

    private void initPop() {
        popButton = ButtonFactory.createImageButton(assets.games.pop);
        popButton.setPosition(GameConstants.WIDTH/2 + 170, 100);
        popButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPopPressed();
            }
        });
    }

    private void onPopPressed() {
        int popType = gameController.onPopPressed();
        switch (popType) {
            case GameController.POP_1:
                GameNode node = stackContainer.pop();
                node.setPopping(true);
                node.setPoppedPosition(1);
                node.setPoppedNodes(poppedNodes);
                //poppedNodes.getPoppedNodes()[1].remove();
                poppedNodes.setPoppedNode(node, 1);
                break;
            case GameController.POP_2:
                GameNode node2 = stackContainer.pop();
                node2.setPopping(true);
                node2.setPoppedPosition(0);
                node2.setPoppedNodes(poppedNodes);
                //poppedNodes.getPoppedNodes()[0].remove();
                poppedNodes.setPoppedNode(node2, 0);
                break;
            case GameController.POP_FAILED:
                break;
            default:
                Gdx.app.debug(TAG, "Unknown pop value return.");

        }
    }

    private void initExpressionsLeft() {
        expressionsLeftText = LabelFactory.createLabel("Expressions left: " + Integer.toString(gameController.getExpressionsLeft()), assets.fonts.defaultBig);
        expressionsLeftText.setPosition(GameConstants.WIDTH - expressionsLeftText.getWidth() - 5, 5);
    }

    private void initPause() {
        pauseButton = ButtonFactory.createImageButton(assets.games.pause);
        pauseButton.setPosition(GameConstants.WIDTH - pauseButton.getWidth() - 10, GameConstants.HEIGHT - pauseButton.getHeight() - 10);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameController.onPausePressed();
            }
        });
    }

    private void initReadySetGoText() {
        readySetGoText = LabelFactory.createLabel("READY", assets.fonts.defaultLarge, Color.RED);
        readySetGoText.setPosition(GameConstants.WIDTH/2-readySetGoText.getWidth()/2, GameConstants.HEIGHT/2-readySetGoText.getHeight()/2);
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

    private void initNodesQueue() {
        List<String> nodesText = gameController.getCurrentExpressionAsList();
        for (String s: nodesText) {
            GameNode node = new GameNode(gameScreen, s);
            nodesQueue.add(node);
        }
    }

    private void initExtras() {
        Pixmap pixmap = new Pixmap(400, 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        lineImage = new Image(texture);
        lineImage.setPosition(GameConstants.WIDTH/2 + 25, GameConstants.HEIGHT-275);

        pixmap = new Pixmap(80,80, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.MAROON);
        for (int i = 77, j=0; i <= 80; i++, j++) {
            pixmap.drawRectangle(j,j,i-j,i-j);
        }

        texture = new Texture(pixmap);
        squareImage = new Image(texture);
        squareImage.setPosition(GameConstants.WIDTH/2-squareImage.getWidth()/2, GameConstants.HEIGHT-squareImage.getHeight()-57);

        pixmap.dispose();
    }

    private void initPoppedNodes() {
        poppedNodes = new PoppedNodes(gameScreen);
        //poppedNodes.getPoppedNodes()[0].setPosition(squareImage.getX()-20-GameNode.WIDTH, squareImage.getY()+squareImage.getHeight()/2-GameNode.HEIGHT/2);
        //poppedNodes.getPoppedNodes()[1].setPosition(squareImage.getX()+squareImage.getWidth()+20, squareImage.getY()+squareImage.getHeight()/2-GameNode.HEIGHT/2);
        poppedNodes.getTempNodes()[0].setPosition(squareImage.getX()-40-GameNode.WIDTH, squareImage.getY()+squareImage.getHeight()/2-GameNode.HEIGHT/2);
        poppedNodes.getTempNodes()[1].setPosition(squareImage.getX()+squareImage.getWidth()+40, squareImage.getY()+squareImage.getHeight()/2-GameNode.HEIGHT/2);
    }

    public void buildStage() {
        Stage stage = gameScreen.getStage();

        stage.addActor(backgroundImage);
        stage.addActor(stopwatchImage);
        stage.addActor(timerText);

        for (Image heart: heartImages) {
            stage.addActor(heart);
        }

        stage.addActor(stackContainer);
        stage.addActor(pushButton);
        stage.addActor(popButton);
        //stage.addActor(pauseButton);
        //stage.addActor(expressionsLeftText);
        stage.addActor(readySetGoText);

        for (GameNode node: nodesQueue) {
            node.setVisible(false);
            stage.addActor(node);
        }

        stage.addActor(lineImage);
        stage.addActor(squareImage);

        for (GameNode tempNode: poppedNodes.getTempNodes()) {
            stage.addActor(tempNode);
        }

        /*
        for (GameNode node: poppedNodes.getPoppedNodes()) {
            stage.addActor(node);
        }
        */
    }

    public void update(float delta) {

        boolean gameStarted = gameController.isGameStarted();

        if(gameStarted) {
            lightUpBackground();
            readySetGoText.remove();
            updateTimer();
            updateLives();
            //updateExpressionsLeft();
            updateNodesQueue();
            if (gameController.hasWin()) {
                Gdx.app.debug(TAG, "Game won.");
                disableButtons();
                switchScreen(new GameWinScreen(gameScreen.getGame()));
            }
            if (gameController.hasLose()) {
                Gdx.app.debug(TAG, "Game lost.");
                disableButtons();
                switchScreen(new GameOverScreen(gameScreen.getGame()));
            }
        }
        else {
            initExtras();
            gameScreen.getStage().getBatch().enableBlending();
            dimBackground();
            updateReadySetGoText();
        }

    }

    private void updateTimer() {
        float time = gameController.getTime();
        if(time < 10 && time > 0) {
            timerText.getStyle().fontColor = Color.RED;
            timerText.setText(String.format(Locale.US, "%.1f", time));
        }
        else {
            timerText.setText(Integer.toString((int) time));
        }
    }

    private void updateLives() {
        for (int i = 0; i < heartImages.length; i++) {
            if(gameController.getLives() <= i) {
                heartImages[i].setColor(Color.GRAY);
            }
            else {
                heartImages[i].setColor(Color.WHITE);
            }
        }
    }

    private void updateExpressionsLeft() {
        expressionsLeftText.setText("Expressions left: " + gameController.getExpressionsLeft());
    }

    private void updateReadySetGoText() {
        readySetGoText.toFront();
        readySetGoText.setText(gameController.getReadySetGoText());
        float width = readySetGoText.getGlyphLayout().width;
        float height = readySetGoText.getGlyphLayout().height;
        readySetGoText.setPosition(GameConstants.WIDTH/2 - width/2, GameConstants.HEIGHT/2 - height/2);
        readySetGoText.setWidth(width);
        readySetGoText.setHeight(height);
    }

    private void updateNodesQueue() {
        for (int i = 0; i < nodesQueue.size(); i++) {
            GameNode node = nodesQueue.get(i);
            node.setVisible(true);
            float nodeWidth = node.getWidth();
            float nodeHeight = node.getHeight();
            if(i==0) {
                node.setPosition(GameConstants.WIDTH/2 - nodeWidth/2, GameConstants.HEIGHT - nodeHeight - 70);
            }
            else {
                node.setPosition(GameConstants.WIDTH/2 - nodeWidth/2 + (5+nodeWidth)*(i-1) + 50, GameConstants.HEIGHT/2 - nodeHeight/2);
            }
        }
    }

    private void dimBackground() {
        Stage stage = gameScreen.getStage();
        stage.addActor(blackImage);
    }

    private void lightUpBackground() {
        blackImage.remove();
    }

    public void removeEquationNodes() {
        for (int i = 0; i < poppedNodes.getPoppedNodes().length; i++) {
            poppedNodes.getPoppedNodes()[i].remove();
        }
        nodesQueue.get(0).setText(gameController.getCurrentExpression().get(0));

    }

    public void switchScreen(final AbstractoScreen nextScreen) {
        Stage stage = gameScreen.getStage();
        stage.getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(delay(5.0f));
        sequenceAction.addAction(fadeOut(2.0f));
        sequenceAction.addAction(run(new Runnable() {
            @Override
            public void run() {
                gameScreen.getGame().setScreen(nextScreen);
            }
        }));
        stage.getRoot().addAction(sequenceAction);
    }

    public void disableButtons() {
        pushButton.setTouchable(Touchable.disabled);
        popButton.setTouchable(Touchable.disabled);
    }

}
