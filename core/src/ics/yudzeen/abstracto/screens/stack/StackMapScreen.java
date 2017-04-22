package ics.yudzeen.abstracto.screens.stack;

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
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.screens.stack.apps.ApplicationsMapScreen;
import ics.yudzeen.abstracto.screens.stack.apps.postfix.PostfixExpressionGameScreen;
import ics.yudzeen.abstracto.screens.stack.duel.DuelScreen;
import ics.yudzeen.abstracto.screens.stack.simulator.StackSimulatorScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of the stack map
 */

public class StackMapScreen extends AbstractoScreen {

    public static final String TAG = StackMapScreen.class.getName();

    private Image backgroundImage;
    private Image locationLabelImage;

    private ImageButton worldMapButton;

    private ImageButton schoolButton;
    private ImageButton simulatorButton;
    private ImageButton applicationsButton;
    private ImageButton duelButton;

    private Label schoolLabel;
    private Label simulatorLabel;
    private Label applicationsLabel;
    private Label duelLabel;

    public StackMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initLocationLabel();
        initWorldMapButton();
        initSchoolButton();
        initSimulatorButton();
        initApplicationsButton();
        initDuelButton();
        initSchoolLabel();
        initSimulatorLabel();
        initApplicationsLabel();
        initDuelLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(locationLabelImage);
        //stage.addActor(worldMapButton);
        stage.addActor(schoolButton);
        stage.addActor(simulatorButton);
        stage.addActor(applicationsButton);
        stage.addActor(duelButton);

        stage.addActor(schoolLabel);
        stage.addActor(simulatorLabel);
        stage.addActor(applicationsLabel);
        stage.addActor(duelLabel);
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

    private void initSchoolButton() {
        schoolButton = ButtonFactory.createImageButton(assets.buttons.book);
        schoolButton.setPosition(80, GameConstants.HEIGHT - schoolButton.getHeight() - 100);
        schoolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 Guru Screen
            }
        });
    }

    private void initSimulatorButton() {
        simulatorButton = ButtonFactory.createImageButton(assets.buttons.simulator);
        simulatorButton.setPosition(schoolButton.getX() + schoolButton.getWidth() + 200, schoolButton.getY());
        simulatorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StackSimulatorScreen(game));
            }
        });
    }

    private void initApplicationsButton() {
        applicationsButton = ButtonFactory.createImageButton(assets.buttons.applications);
        applicationsButton.setPosition(320, 50);
        applicationsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ApplicationsMapScreen(game));
            }
        });
    }

    private void initDuelButton() {
        duelButton = ButtonFactory.createImageButton(assets.buttons.duel);
        duelButton.setPosition(applicationsButton.getX() + applicationsButton.getWidth() + 220, applicationsButton.getY());
        duelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new DuelScreen(game));
            }
        });
    }

    private void initSchoolLabel() {
        schoolLabel = LabelFactory.createLabel("INFO", assets.fonts.defaultBig, Color.WHITE);
        schoolLabel.setPosition(schoolButton.getX() + schoolButton.getWidth()/2 - schoolLabel.getWidth()/2,
                schoolButton.getY() - schoolLabel.getHeight() - 5);
    }

    private void initSimulatorLabel() {
        simulatorLabel = LabelFactory.createLabel("SIMULATOR", assets.fonts.defaultBig, Color.WHITE);
        simulatorLabel.setPosition(simulatorButton.getX() + simulatorButton.getWidth()/2 - simulatorLabel.getWidth()/2,
                simulatorButton.getY() - simulatorLabel.getHeight() - 5);
    }

    private void initApplicationsLabel() {
        applicationsLabel = LabelFactory.createLabel("APPLICATIONS", assets.fonts.defaultBig, Color.WHITE);
        applicationsLabel.setPosition(applicationsButton.getX() + applicationsButton.getWidth()/2 - applicationsLabel.getWidth()/2,
                applicationsButton.getY() - applicationsLabel.getHeight() - 5);
    }

    private void initDuelLabel() {
        duelLabel = LabelFactory.createLabel("DUEL", assets.fonts.defaultBig, Color.WHITE);
        duelLabel.setPosition(duelButton.getX() + duelButton.getWidth()/2 - duelLabel.getWidth()/2,
                duelButton.getY() - duelLabel.getHeight() - 5);
    }
}
