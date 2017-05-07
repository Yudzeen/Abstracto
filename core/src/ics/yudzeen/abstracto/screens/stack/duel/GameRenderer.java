package ics.yudzeen.abstracto.screens.stack.duel;

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

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.objects.Cloud;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;


/**
 * Renderer for duel
 */

class GameRenderer {

    private DuelScreen gameScreen;
    private Assets assets;
    private GameController gameController;

    private Image backgroundImage;
    private Image playerImage;
    private Image enemyImage;
    private Image blueSquare;   // player image background
    private Image redSquare;    // enemy image background
    private Image playerBodyImage;
    private Image enemyBodyImage;
    private List<Cloud> cloudList;

    private HPBar playerHPBar;
    private HPBar enemyHPBar;
    private Portal portal;

    private Label vsLabel;

    private ImageButton pushButton;
    private ImageButton popButton;

    public GameRenderer(DuelScreen gameScreen, GameController gameController) {
        this.gameScreen = gameScreen;
        this.gameController = gameController;
        init();
    }

    private void init() {
        assets = gameScreen.getAssets();
        initBackgroundImage();
        initPlayerImage();
        initEnemyImage();
        initBlueSquare();
        initRedSquare();
        initVsLabel();
        initPlayerBodyImage();
        initEnemyBodyImage();
        initPlayerHPBar();
        initEnemyHPBar();
        initPushButton();
        initPopButton();
        initPortal();
        initClouds();
    }

    void buildStage(Stage stage) {
        stage.addActor(backgroundImage);
        for (Cloud cloud: cloudList) {
            stage.addActor(cloud);
        }
        stage.addActor(blueSquare);
        stage.addActor(redSquare);
        stage.addActor(playerImage);
        stage.addActor(enemyImage);
        stage.addActor(vsLabel);
        stage.addActor(playerBodyImage);
        stage.addActor(enemyBodyImage);
        stage.addActor(playerHPBar);
        stage.addActor(enemyHPBar);
        stage.addActor(pushButton);
        stage.addActor(popButton);
        stage.addActor(portal);
    }

    void render() {
        if(gameController.gameOver) {
            disablePushAndPopButtons(true);
            final Abstracto game = gameScreen.getGame();
            Stage stage = gameScreen.getStage();
            stage.getRoot().getColor().a = 1;
            SequenceAction sequenceAction = new SequenceAction();
            float delay = 1.0f;
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

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_arena);
    }

    private void initPushButton() {
        pushButton = ButtonFactory.createImageButton(assets.games.push);
        pushButton.setPosition(GameConstants.WIDTH/4 - pushButton.getWidth()/2 + 20, 30);
        pushButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPushPressed();
            }
        });
    }

    private void initPopButton() {
        popButton = ButtonFactory.createImageButton(assets.games.pop);
        popButton.setPosition(GameConstants.WIDTH*3/4 - popButton.getWidth()/2 - 20, 30);
        popButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onPopPressed();
            }
        });
    }

    private void initPlayerImage() {
        GamePreferences gamePreferences = gameScreen.getGame().getGamePreferences();
        playerImage = new Image(gamePreferences.character.equals("MALE") ? assets.images.male_mugshot : assets.images.female_mugshot);
        final float scaleFactor = 0.35f;
        playerImage.setScale(scaleFactor);
        playerImage.setPosition(20, GameConstants.HEIGHT - playerImage.getHeight()*scaleFactor - 20);
    }

    private void initEnemyImage() {
        enemyImage = new Image(assets.images.teacher_mugshot);
        final float scaleFactor = 0.35f;
        enemyImage.setScale(scaleFactor);
        enemyImage.setPosition(GameConstants.WIDTH - enemyImage.getWidth()*scaleFactor - 20, GameConstants.HEIGHT - playerImage.getHeight()*scaleFactor - 20);
    }

    private void initBlueSquare() {
        int width = (int) (playerImage.getWidth() * 0.40f);
        int height = (int) (playerImage.getHeight() * 0.40f);
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        pixmap.setColor(Color.BLACK);
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i<5 || i>pixmap.getWidth()-6 || j<5 || j>pixmap.getHeight()-6) {
                    pixmap.drawPixel(i, j);
                }
            }
        }
        blueSquare = new Image(new Texture(pixmap));
        pixmap.dispose();
        blueSquare.setPosition(playerImage.getX() - 7, playerImage.getY() - 4);
    }

    private void initRedSquare() {
        int width = (int) (playerImage.getWidth() * 0.40f);
        int height = (int) (playerImage.getHeight() * 0.40f);
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        pixmap.setColor(Color.BLACK);
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i<5 || i>pixmap.getWidth()-6 || j<5 || j>pixmap.getHeight()-6) {
                    pixmap.drawPixel(i, j);
                }
            }
        }
        redSquare = new Image(new Texture(pixmap));
        pixmap.dispose();
        redSquare.setPosition(enemyImage.getX() - 5, enemyImage.getY() - 4);
    }

    private void initVsLabel() {
        vsLabel = LabelFactory.createLabel("VS", assets.fonts.verdana_50, Color.FIREBRICK);
        vsLabel.setPosition(GameConstants.WIDTH/2 - vsLabel.getWidth()/2, GameConstants.HEIGHT - vsLabel.getHeight() - 20);
    }

    private void initPlayerBodyImage() {
        GamePreferences gamePreferences = gameScreen.getGame().getGamePreferences();
        playerBodyImage = new Image(gamePreferences.character.equals("MALE") ? assets.images.male : assets.images.female);
        playerBodyImage.setScale(0.5f);
        playerBodyImage.setPosition(GameConstants.WIDTH/4 - 75, 100);
    }

    private void initEnemyBodyImage() {
        enemyBodyImage = new Image(assets.images.teacher);
        enemyBodyImage.setScale(0.5f);
        enemyBodyImage.setPosition(GameConstants.WIDTH*3/4, 100);
    }

    private void initPlayerHPBar() {
        playerHPBar = new HPBar(224, 50, gameController.selfHP, false);
        playerHPBar.setPosition(blueSquare.getX() + blueSquare.getWidth(), blueSquare.getY());
    }

    private void initEnemyHPBar() {
        enemyHPBar = new HPBar(224, 50, gameController.enemyHP, true);
        enemyHPBar.setPosition(redSquare.getX() - enemyHPBar.getWidth(), redSquare.getY());
    }

    private void initPortal() {
        portal = new Portal(gameScreen, gameController.pushPopList);
        portal.setPosition(GameConstants.WIDTH/2 - portal.getWidth()/2, 30);
    }

    private void initClouds() {
        cloudList = new ArrayList<>();
        cloudList.add(new Cloud(gameScreen.getGame(), Cloud.TYPE_1));
        cloudList.add(new Cloud(gameScreen.getGame(), Cloud.TYPE_2));
        cloudList.add(new Cloud(gameScreen.getGame(), Cloud.TYPE_3));
    }

    private void onPushPressed() {
        if(gameController.onPushPressed()) {
            enemyHPBar.setCurrentHP(enemyHPBar.getCurrentHP() - 1);
        }
        else {
            playerHPBar.setCurrentHP(playerHPBar.getCurrentHP() - 1);
        }
        portal.nextPortalImage();
    }

    private void onPopPressed() {
        if(gameController.onPopPressed()) {
            enemyHPBar.setCurrentHP(enemyHPBar.getCurrentHP() - 1);
        }
        else {
            playerHPBar.setCurrentHP(playerHPBar.getCurrentHP() - 1);
        }
        portal.nextPortalImage();
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

}
