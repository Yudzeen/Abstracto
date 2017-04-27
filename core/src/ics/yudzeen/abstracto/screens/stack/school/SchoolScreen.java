package ics.yudzeen.abstracto.screens.stack.school;

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
import ics.yudzeen.abstracto.screens.stack.school.applications.ApplicationsScreen;
import ics.yudzeen.abstracto.screens.stack.school.info.InfoPage1;
import ics.yudzeen.abstracto.screens.stack.simulator.StackSimulatorScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Stack - School Screen
 */

public class SchoolScreen extends AbstractoScreen {

    static final String TAG = SchoolScreen.class.getName();

    private Image backgroundImage;

    private ImageButton infoButton;
    private ImageButton appsButton;
    private ImageButton simulatorButton;
    private ImageButton backButton;

    private Label titleLabel;
    private Label infoLabel;
    private Label appsLabel;
    private Label simulatorLabel;

    public SchoolScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleLabel();
        initInfoButton();
        initAppsButton();
        initSimulatorButton();
        initInfoLabel();
        initAppsLabel();
        initSimulatorLabel();
        initBackButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(infoButton);
        stage.addActor(appsButton);
        stage.addActor(simulatorButton);
        stage.addActor(infoLabel);
        stage.addActor(appsLabel);
        stage.addActor(simulatorLabel);
        stage.addActor(backButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.setColor(0,0,0,1);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initTitleLabel() {
        titleLabel = LabelFactory.createLabel("STACK", assets.fonts.defaultVeryBig, Color.WHITE);
        titleLabel.setPosition(GameConstants.WIDTH/2 - titleLabel.getWidth()/2,
                GameConstants.HEIGHT - titleLabel.getHeight() - 10);
    }

    private void initBackButton() {
        backButton = ButtonFactory.createImageButton(assets.buttons.back);
        backButton.setPosition(10, GameConstants.HEIGHT - backButton.getHeight() - 10);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StackMapScreen(game));
            }
        });
    }

    private void initInfoButton() {
        infoButton = ButtonFactory.createImageButton(assets.buttons.book);
        infoButton.setPosition(200, GameConstants.HEIGHT - infoButton.getHeight() - 100);
        infoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InfoPage1(game));
            }
        });
    }

    private void initAppsButton() {
        appsButton = ButtonFactory.createImageButton(assets.buttons.apps);
        appsButton.setPosition(infoButton.getX() + infoButton.getWidth()/2 - appsButton.getWidth()/2,
                infoButton.getY() - appsButton.getHeight() - 20);
        appsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ApplicationsScreen(game));
            }
        });
    }

    private void initSimulatorButton() {
        simulatorButton = ButtonFactory.createImageButton(assets.buttons.simulator);
        simulatorButton.setPosition(appsButton.getX() + appsButton.getWidth()/2 - simulatorButton.getWidth()/2,
                appsButton.getY() - simulatorButton.getHeight() - 20);
        simulatorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StackSimulatorScreen(game));
            }
        });
    }

    private void initInfoLabel() {
        infoLabel = LabelFactory.createLabel("INFO", assets.fonts.defaultBig, Color.WHITE);
        infoLabel.setPosition(infoButton.getX() + infoButton.getWidth() + 40,
                infoButton.getY() + infoButton.getHeight()/2 - infoLabel.getHeight()/2);
    }

    private void initAppsLabel() {
        appsLabel = LabelFactory.createLabel("APPLICATIONS", assets.fonts.defaultBig, Color.WHITE);
        appsLabel.setPosition(infoLabel.getX(),
                appsButton.getY() + appsButton.getHeight()/2 - appsLabel.getHeight()/2);
    }

    private void initSimulatorLabel() {
        simulatorLabel = LabelFactory.createLabel("SIMULATOR", assets.fonts.defaultBig, Color.WHITE);
        simulatorLabel.setPosition(appsLabel.getX(),
                simulatorButton.getY() + simulatorButton.getHeight()/2 - simulatorLabel.getHeight()/2);
    }

}
