package ics.yudzeen.abstracto.screens.stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.Text;

/**
 * Created by Yujin on 20/02/2017.
 */

public class StackPostfixExpressionGameScreen extends AbstractoScreen {

    public static String TAG = StackPostfixExpressionGameScreen.class.getName();

    public static final int MAX_LIVES = 3;

    int lives;

    public StackPostfixExpressionGameScreen(Abstracto game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildBackground();
        buildLives();
        buildTimer();
        buildPause();
        buildPush();
        buildPop();
        buildStack();
        buildExpressionsLeft();
    }

    private void buildBackground() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.draw(pixmap,0,0);
        Image background = new Image(texture);
        stage.addActor(background);
    }

    private void buildLives() {
        lives = 2;

        for(int i=0;i<MAX_LIVES;i++) {
            Image heartImg = new Image(assets.games.heart);
            heartImg.setPosition(10+i*40, GameConstants.HEIGHT-heartImg.getHeight()-75);
            if(lives<=i)
                heartImg.setColor(Color.GRAY);
            else
                heartImg.setColor(Color.RED);
            stage.addActor(heartImg);
        }
    }

    private void buildTimer() {
        int time = 60;

        Image stopwatchImg = new Image(assets.games.stopwatch);
        Text timer = new Text(Integer.toString(time), assets.fonts.defaultVeryBig, Color.BLACK);

        stopwatchImg.setPosition(0, GameConstants.HEIGHT - stopwatchImg.getHeight() - 10);
        timer.setPosition(60, GameConstants.HEIGHT - 25);
        stage.addActor(stopwatchImg);
        stage.addActor(timer);
    }

    private void buildPause() {
        ImageButton pauseButton = ButtonFactory.createImageButton(assets.games.pause);
        pauseButton.setPosition(GameConstants.WIDTH-pauseButton.getWidth()-10, GameConstants.HEIGHT-pauseButton.getHeight()-10);
        stage.addActor(pauseButton);
    }

    private void buildStack() {
        Image stackImg = new Image(assets.simulator.stack_container);
        stackImg.setPosition(200,0);
        stage.addActor(stackImg);
    }

    private void buildPush() {
        ImageButton pushButton = ButtonFactory.createImageButton(assets.games.push);
        pushButton.setPosition(GameConstants.WIDTH/2 - 20, 100);
        stage.addActor(pushButton);
    }

    private void buildPop() {
        ImageButton popButton = ButtonFactory.createImageButton(assets.games.pop);
        popButton.setPosition(GameConstants.WIDTH/2 + 170, 100);
        stage.addActor(popButton);
    }

    private void buildExpressionsLeft() {
        int expressionsLeft = 5;
        Text text = new Text("Expressions Left: " + Integer.toString(expressionsLeft),assets.fonts.defaultBig,Color.BLACK);
        text.setPosition(GameConstants.WIDTH-250, 35);
        stage.addActor(text);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }
}
