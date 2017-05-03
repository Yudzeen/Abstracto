package ics.yudzeen.abstracto.screens.queue.games.processscheduling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Process scheduling renderer
 */

class GameRenderer {

    static final String TAG = GameRenderer.class.getName();

    private ProcessSchedulingGameScreen gameScreen;
    private GameController gameController;
    private Assets assets;

    private Image backgroundImage;
    private Label readySetGoLabel;

    private Image blackImage; // for dimming

    private Image stopwatchImage;
    private Label timerLabel;

    List<QueueContainer> queueContainerList;
    List<Chip> chipList;
    ProcessArea processArea;

    public GameRenderer(ProcessSchedulingGameScreen gameScreen, GameController gameController) {
        this.gameScreen = gameScreen;
        this.gameController = gameController;
        assets = gameScreen.getAssets();
        init();
    }

    private void init() {
        initBackgroundImage();
        initReadySetGoLabel();
        initTimer();
        initQueueContainerList();
        initChips();
        initProcessArea();

        initBlackImage();
    }

    void render(Stage stage) {
        boolean gameStarted = gameController.gameStarted;

        if(gameStarted) {
            blackImage.setVisible(false);
            readySetGoLabel.remove();
            renderTimer();
            processArea.setSpawnProcess(true);
            if(hasWin()) {
                gameController.gameOver = true;
                gameController.gameWin = true;
                toGameOverScreen(stage);
            }
        }
        else {
            blackImage.setVisible(true);
            renderReadySetGoText();
        }

        if(gameController.gameOver) {
            toGameOverScreen(stage);
        }
    }

    void buildStage(Stage stage) {
        stage.addActor(backgroundImage);
        stage.addActor(readySetGoLabel);
        stage.addActor(stopwatchImage);
        stage.addActor(timerLabel);
        for (Chip chip : chipList) {
            stage.addActor(chip);
        }
        for (QueueContainer queueContainer: queueContainerList) {
            stage.addActor(queueContainer);
        }
        stage.addActor(processArea);

        stage.addActor(blackImage);
        for (Process process : processArea.getProcessList()) {
            stage.addActor(process);
        }
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initReadySetGoLabel() {
        readySetGoLabel = LabelFactory.createLabel("READY", assets.fonts.defaultLarge, Color.RED);
        readySetGoLabel.setPosition(GameConstants.WIDTH/2-readySetGoLabel.getWidth()/2, GameConstants.HEIGHT/2-readySetGoLabel.getHeight()/2);
    }

    private void initTimer() {
        stopwatchImage = new Image(assets.games.stopwatch);
        stopwatchImage.setPosition(0, GameConstants.HEIGHT - stopwatchImage.getHeight() - 10);

        timerLabel = LabelFactory.createLabel(Integer.toString(GameController.TIME_LIMIT), assets.fonts.defaultVeryBig);
        timerLabel.setPosition(65, GameConstants.HEIGHT - timerLabel.getHeight() - 10);
    }

    private void initQueueContainerList() {
        queueContainerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            QueueContainer queueContainer = new QueueContainer(gameScreen.getGame(), i);
            queueContainer.setPosition(GameConstants.WIDTH - 79 - queueContainer.getWidth() - 30,
                    50 + 109*i);
            queueContainerList.add(queueContainer);
        }
    }

    private void initChips() {
        chipList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            QueueContainer queueContainer = queueContainerList.get(i);
            Chip chip = new Chip(gameScreen, queueContainer);
            chip.setPosition(GameConstants.WIDTH - chip.getWidth() - 30,
                    queueContainer.getY() + queueContainer.getHeight()/2 - chip.getHeight()/2);
            chipList.add(chip);
        }
    }

    private void initProcessArea() {
        processArea = new ProcessArea(gameScreen, this);
        processArea.setPosition(50,45);
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

    private void renderReadySetGoText() {
        readySetGoLabel.toFront();
        readySetGoLabel.setText(gameController.readySetGoText);
        float width = readySetGoLabel.getGlyphLayout().width;
        float height = readySetGoLabel.getGlyphLayout().height;
        readySetGoLabel.setPosition(GameConstants.WIDTH/2 - width/2, GameConstants.HEIGHT/2 - height/2);
        readySetGoLabel.setWidth(width);
        readySetGoLabel.setHeight(height);
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

    private boolean hasWin() {
        Gdx.app.debug(TAG, "Process list empty: " + processArea.getProcessList().isEmpty() + " Process list: " + processArea.getProcessList() +
                " Queues empty: " + allQueuesIsEmpty() + " Timer: " + (gameController.timer < 25));
        return processArea.getProcessList().isEmpty() && allQueuesIsEmpty() && gameController.timer < 25;
    }

    private boolean allQueuesIsEmpty() {
        for (QueueContainer queueContainer :queueContainerList) {
            if (queueContainer.size() != 0) {
                return false;
            }
        }
        return true;
    }

    private void toGameOverScreen(Stage stage) {
        final Abstracto game = gameScreen.getGame();
        stage.getRoot().getColor().a = 1;
        SequenceAction sequenceAction = new SequenceAction();
        float delay = gameController.gameWin ? 1.0f : 0.5f;
        sequenceAction.addAction(delay(delay));
        sequenceAction.addAction(fadeOut(2.0f));
        sequenceAction.addAction(run(new Runnable() {
            @Override
            public void run() {
                game.setScreen(new GameOverScreen(game, gameController.gameWin));
            }
        }));
        stage.addAction(sequenceAction);
    }
}
