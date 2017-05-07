package ics.yudzeen.abstracto.screens.stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.WorldMapScreen;
import ics.yudzeen.abstracto.screens.stack.duel.ArenaConversationScreen;
import ics.yudzeen.abstracto.screens.stack.duel.InstructionScreen;
import ics.yudzeen.abstracto.screens.stack.games.ArcadeConversationScreen;
import ics.yudzeen.abstracto.screens.stack.games.ArcadeMapScreen;
import ics.yudzeen.abstracto.screens.stack.school.SchoolScreen;
import ics.yudzeen.abstracto.screens.stack.school.TeacherScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Screen of the stack map
 */

public class StackMapScreen extends AbstractoScreen {

    public static final String TAG = StackMapScreen.class.getName();

    public static final Color CELADON = new Color(185/255.0f, 213/255.0f, 165/255.0f, 1.0f);
    public static final Color LABEL_COLOR = new Color(56/255.0f, 63/255.0f, 50/255.0f, 1.0f);

    private Image backgroundImage;
    private Image locationLabelImage;

    private ImageButton worldMapButton;

    private ImageButton schoolButton;
    private ImageButton arcadeButton;
    private ImageButton arenaButton;

    private Label schoolLabel;
    private Label arcadeLabel;
    private Label arenaLabel;

    private Image schoolDescBackground;
    private Label schoolDescLabel;

    private Image arcadeDescBackground;
    private Label arcadeDescLabel;

    private Image arenaDescBackground;
    private Label arenaDescLabel;

    public StackMapScreen(Abstracto game) {
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

        initSchoolDescBackground();
        initSchoolDescLabel();

        initArcadeDescBackground();
        initArcadeDescLabel();

        initArenaDescBackground();
        initArenaDescLabel();
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

        stage.addActor(schoolDescBackground);
        stage.addActor(schoolDescLabel);

        stage.addActor(arcadeDescBackground);
        stage.addActor(arcadeDescLabel);

        stage.addActor(arenaDescBackground);
        stage.addActor(arenaDescLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new WorldMapScreen(game));
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        //pixmap.setColor(0,0,0,1);
        pixmap.setColor(CELADON);
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
        schoolButton = ButtonFactory.createImageButton(assets.buttons.school);
        schoolButton.setPosition(80, GameConstants.HEIGHT - schoolButton.getHeight() - 100);
        schoolButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GamePreferences gamePreferences = game.getGamePreferences();
                gamePreferences.load();
                if(gamePreferences.stackSchoolDialogueDone) {
                    game.setScreen(new SchoolScreen(game));
                }
                else {
                    game.setScreen(new TeacherScreen(game));
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                schoolDescBackground.setVisible(true);
                schoolDescLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                schoolDescBackground.setVisible(false);
                schoolDescLabel.setVisible(false);
            }

        });
        schoolButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                schoolDescBackground.setVisible(true);
                schoolDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                schoolDescBackground.setVisible(false);
                schoolDescLabel.setVisible(false);
            }
        });
    }

    private void initSchoolDescBackground() {
        Pixmap pixmap = new Pixmap(180, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(153/255.0f, 175/255.0f, 137/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(111/255.0f, 127/255.0f, 99/255.0f, 0.9f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        schoolDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        schoolDescBackground.setPosition(schoolButton.getX() + schoolButton.getWidth()*3/4,
                schoolButton.getY() + schoolButton.getHeight()/4);
        schoolDescBackground.setVisible(false);
    }

    private void initSchoolDescLabel() {
        String description = "Learn and simulate \nstack";
        schoolDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        schoolDescLabel.setPosition(schoolDescBackground.getX() + schoolDescBackground.getWidth()/2 - schoolDescLabel.getWidth()/2,
                schoolDescBackground.getY() + schoolDescBackground.getHeight()/2 - schoolDescLabel.getHeight()/2);
        schoolDescLabel.setVisible(false);
    }

    private void initArcadeButton() {
        arcadeButton = ButtonFactory.createImageButton(assets.buttons.arcade);
        arcadeButton.setPosition(schoolButton.getX() + schoolButton.getWidth() + 200, schoolButton.getY());
        arcadeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GamePreferences gamePreferences = game.getGamePreferences();
                gamePreferences.load();
                if(gamePreferences.stackArcadeDialogueDone) {
                    game.setScreen(new ArcadeMapScreen(game));
                }
                else {
                    game.setScreen(new ArcadeConversationScreen(game));
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                arcadeDescBackground.setVisible(true);
                arcadeDescLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                arcadeDescBackground.setVisible(false);
                arcadeDescLabel.setVisible(false);
            }

        });
        arcadeButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                arcadeDescBackground.setVisible(true);
                arcadeDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                arcadeDescBackground.setVisible(false);
                arcadeDescLabel.setVisible(false);
            }
        });
    }

    private void initArcadeDescBackground() {
        Pixmap pixmap = new Pixmap(180, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(153/255.0f, 175/255.0f, 137/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(111/255.0f, 127/255.0f, 99/255.0f, 0.9f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        arcadeDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        arcadeDescBackground.setPosition(arcadeButton.getX() + arcadeButton.getWidth()/4,
                arcadeButton.getY() + arcadeButton.getHeight()*3/4);
        arcadeDescBackground.setVisible(false);
    }

    private void initArcadeDescLabel() {
        String description = "Play and learn \nstack applications";
        arcadeDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        arcadeDescLabel.setPosition(arcadeDescBackground.getX() + arcadeDescBackground.getWidth()/2 - arcadeDescLabel.getWidth()/2,
                arcadeDescBackground.getY() + arcadeDescBackground.getHeight()/2 - arcadeDescLabel.getHeight()/2);
        arcadeDescLabel.setVisible(false);
    }

    private void initArenaButton() {
        arenaButton = ButtonFactory.createImageButton(assets.buttons.arena);
        arenaButton.setPosition(320, 50);
        arenaButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GamePreferences gamePreferences = game.getGamePreferences();
                gamePreferences.load();
                if(gamePreferences.stackArenaDialogueDone) {
                    game.setScreen(new InstructionScreen(game));
                }
                else {
                    game.setScreen(new ArenaConversationScreen(game));
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                arenaDescBackground.setVisible(true);
                arenaDescLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                arenaDescBackground.setVisible(false);
                arenaDescLabel.setVisible(false);
            }
        });
        arenaButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                arenaDescBackground.setVisible(true);
                arenaDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                arenaDescBackground.setVisible(false);
                arenaDescLabel.setVisible(false);
            }
        });
    }

    private void initArenaDescBackground() {
        Pixmap pixmap = new Pixmap(180, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(153/255.0f, 175/255.0f, 137/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(111/255.0f, 127/255.0f, 99/255.0f, 0.9f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        arenaDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        arenaDescBackground.setPosition(arenaButton.getX() + arenaButton.getWidth()/4,
                arenaButton.getY() + arenaButton.getHeight()*3/4);
        arenaDescBackground.setVisible(false);
    }

    private void initArenaDescLabel() {
        String description = "Test your skills \nagainst the stack \nmaster";
        arenaDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        arenaDescLabel.setPosition(arenaDescBackground.getX() + arenaDescBackground.getWidth()/2 - arenaDescLabel.getWidth()/2,
                arenaDescBackground.getY() + arenaDescBackground.getHeight()/2 - arenaDescLabel.getHeight()/2);
        arenaDescLabel.setVisible(false);
    }

    private void initSchoolLabel() {
        schoolLabel = LabelFactory.createLabel("SCHOOL", assets.fonts.verdana_30, LABEL_COLOR);
        schoolLabel.setPosition(schoolButton.getX() + schoolButton.getWidth()/2 - schoolLabel.getWidth()/2,
                schoolButton.getY() - schoolLabel.getHeight() - 5);
    }

    private void initArcadeLabel() {
        arcadeLabel = LabelFactory.createLabel("ARCADE", assets.fonts.verdana_30, LABEL_COLOR);
        arcadeLabel.setPosition(arcadeButton.getX() + arcadeButton.getWidth()/2 - arcadeLabel.getWidth()/2,
                arcadeButton.getY() - arcadeLabel.getHeight() - 5);
    }

    private void initArenaLabel() {
        arenaLabel = LabelFactory.createLabel("ARENA", assets.fonts.verdana_30, LABEL_COLOR);
        arenaLabel.setPosition(arenaButton.getX() + arenaButton.getWidth()/2 - arenaLabel.getWidth()/2,
                arenaButton.getY() - arenaLabel.getHeight() - 5);
    }

}
