package ics.yudzeen.abstracto.screens.queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.screens.queue.duel.InstructionScreen;
import ics.yudzeen.abstracto.screens.queue.games.ArcadeMapScreen;
import ics.yudzeen.abstracto.screens.queue.school.SchoolScreen;
import ics.yudzeen.abstracto.screens.stack.duel.DuelScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of the queue map
 */

public class QueueMapScreen extends AbstractoScreen {

    public static final String TAG = QueueMapScreen.class.getName();

    private Image backgroundImage;
    private Image locationLabelImage;

    private ImageButton worldMapButton;

    private ImageButton schoolButton;
    private ImageButton arcadeButton;
    private ImageButton arenaButton;

    private Label schoolLabel;
    private Label arcadeLabel;
    private Label arenaLabel;

    public QueueMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initLocationLabel();
        initWorldMapButton();
        initSchoolButton();
        initArcadeButton();
        initArenaButton();
        initSchoolLabel();
        initArcadeLabel();
        initArenaLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(locationLabelImage);
        stage.addActor(worldMapButton);
        stage.addActor(schoolButton);
        stage.addActor(arenaButton);
        stage.addActor(arcadeButton);

        stage.addActor(schoolLabel);
        stage.addActor(arcadeLabel);
        stage.addActor(arenaLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new WorldMapScreen(game));
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_grass);
        backgroundImage.setPosition(0,0);
    }

    private void initLocationLabel() {
        locationLabelImage = new Image(assets.images.location_queue);
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
        schoolButton = ButtonFactory.createImageButton(assets.buttons.school);
        schoolButton.setPosition(80, GameConstants.HEIGHT - schoolButton.getHeight() - 150);
        schoolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SchoolScreen(game));
            }
        });
    }

    private void initArcadeButton() {
        arcadeButton = ButtonFactory.createImageButton(assets.buttons.arcade);
        arcadeButton.setPosition(320, 50);
        arcadeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ArcadeMapScreen(game));
            }
        });
    }

    private void initArenaButton() {
        arenaButton = ButtonFactory.createImageButton(assets.buttons.arena);
        arenaButton.setPosition(schoolButton.getX() + schoolButton.getWidth() + 200, schoolButton.getY() + 50);
        arenaButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InstructionScreen(game));
            }
        });
    }

    private void initSchoolLabel() {
        schoolLabel = LabelFactory.createLabel("SCHOOL", assets.fonts.defaultBig, Color.WHITE);
        schoolLabel.setPosition(schoolButton.getX() + schoolButton.getWidth()/2 - schoolLabel.getWidth()/2,
                schoolButton.getY() - schoolLabel.getHeight() - 5);
    }

    private void initArcadeLabel() {
        arcadeLabel = LabelFactory.createLabel("ARCADE", assets.fonts.defaultBig, Color.WHITE);
        arcadeLabel.setPosition(arcadeButton.getX() + arcadeButton.getWidth()/2 - arcadeLabel.getWidth()/2,
                arcadeButton.getY() - arcadeLabel.getHeight() - 5);
    }

    private void initArenaLabel() {
        arenaLabel = LabelFactory.createLabel("ARENA", assets.fonts.defaultBig, Color.WHITE);
        arenaLabel.setPosition(arenaButton.getX() + arenaButton.getWidth()/2 - arenaLabel.getWidth()/2,
                arenaButton.getY() - arenaLabel.getHeight() - 5);
    }
}
