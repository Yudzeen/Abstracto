package ics.yudzeen.abstracto.screens.stack;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.screens.stack.postfix.PostfixExpressionGameScreen;
import ics.yudzeen.abstracto.screens.stack.simulator.StackSimulatorScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of the stack map
 */

public class StackMapScreen extends AbstractoScreen {

    public static final String TAG = StackMapScreen.class.getName();

    private Image backgroundImage;
    private Image locationLabelImage;

    private ImageButton worldMapButton;

    private TextButton guruButton;
    private TextButton simulatorButton;
    private TextButton postfixGameButton;
    private TextButton balancingSymbolsButton;
    private TextButton bossFightButton;

    public StackMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initLocationLabel();
        initWorldMapButton();
        initGuruButton();
        initSimulatorButton();
        initPostfixGameButton();
        initBalancingSymbolsButton();
        initBossFightButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(locationLabelImage);
        stage.addActor(worldMapButton);
        stage.addActor(guruButton);
        stage.addActor(simulatorButton);
        stage.addActor(postfixGameButton);
        stage.addActor(balancingSymbolsButton);
        stage.addActor(bossFightButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new WorldMapScreen(game));
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initLocationLabel() {
        locationLabelImage = new Image(assets.images.location_stack);
        locationLabelImage.setPosition(0, GameConstants.HEIGHT - locationLabelImage.getHeight());
    }

    private void initWorldMapButton() {
        worldMapButton = ButtonFactory.createImageButton(assets.buttons.map);
        worldMapButton.setPosition(GameConstants.WIDTH - worldMapButton.getWidth() - 10,
                GameConstants.HEIGHT - worldMapButton.getHeight() - 10);
        worldMapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WorldMapScreen(game));
            }
        });
    }

    private void initGuruButton() {
        guruButton = ButtonFactory.createTextButton("GURU");
        guruButton.setPosition(30, GameConstants.HEIGHT - guruButton.getHeight() - 100);
        guruButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 Guru Screen
            }
        });
    }

    private void initSimulatorButton() {
        simulatorButton = ButtonFactory.createTextButton("SIMULATOR");
        simulatorButton.setPosition(30, guruButton.getY() - simulatorButton.getHeight() - 10);
        simulatorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StackSimulatorScreen(game));
            }
        });
    }

    private void initPostfixGameButton() {
        postfixGameButton = ButtonFactory.createTextButton("POSTFIX EXPRESSION EVALUATION");
        postfixGameButton.setPosition(30, simulatorButton.getY() - postfixGameButton.getHeight() - 10);
        postfixGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PostfixExpressionGameScreen(game));
            }
        });
    }

    private void initBalancingSymbolsButton() {
        balancingSymbolsButton = ButtonFactory.createTextButton("BALANCING SYMBOLS");
        balancingSymbolsButton.setPosition(30, postfixGameButton.getY() - balancingSymbolsButton.getHeight() - 10);
        balancingSymbolsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 Balancing symbols game
            }
        });
    }

    private void initBossFightButton() {
        bossFightButton = ButtonFactory.createTextButton("BOSS FIGHT");
        bossFightButton.setPosition(30, balancingSymbolsButton.getY() - bossFightButton.getHeight() - 10);
        bossFightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 Boss fight game
            }
        });
    }
}
