package ics.yudzeen.abstracto.screens.queue.school;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.queue.school.info.InfoPage1;
import ics.yudzeen.abstracto.screens.queue.simulator.QueueSimulatorScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.school.applications.ApplicationsScreen;
import ics.yudzeen.abstracto.screens.stack.school.simulator.StackSimulatorScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * School Screen
 */

public class SchoolScreen extends AbstractoScreen {

    static final String TAG = SchoolScreen.class.getName();

    private Image backgroundImage;
    private Image teacherImage;

    private ImageButton infoButton;
    private ImageButton appsButton;
    private ImageButton simulatorButton;
    private ImageButton backButton;

    private Label titleLabel;
    private Label infoLabel;
    private Label appsLabel;
    private Label simulatorLabel;

    private Image infoDescBackground;
    private Label infoDescLabel;

    private Image simulatorDescBackground;
    private Label simulatorDescLabel;

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

        initInfoDescBackground();
        initInfoDescLabel();

        initSimulatorDescBackground();
        initSimulatorDescLabel();

        initTeacherImage();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(titleLabel);
        stage.addActor(infoButton);
        //stage.addActor(appsButton);
        stage.addActor(simulatorButton);
        stage.addActor(infoLabel);
        //stage.addActor(appsLabel);
        stage.addActor(simulatorLabel);
        //stage.addActor(backButton);

        stage.addActor(infoDescBackground);
        stage.addActor(infoDescLabel);

        stage.addActor(simulatorDescBackground);
        stage.addActor(simulatorDescLabel);

        stage.addActor(teacherImage);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new QueueMapScreen(game));
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.setColor(0,0,0,1);
        //pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        pixmap.setColor(QueueMapScreen.THEME);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initTitleLabel() {
        titleLabel = LabelFactory.createLabel("QUEUE SCHOOL", assets.fonts.verdana_40, QueueMapScreen.LABEL_COLOR);
        titleLabel.setPosition(GameConstants.WIDTH/2 - titleLabel.getWidth()/2,
                GameConstants.HEIGHT - titleLabel.getHeight() - 30);
    }

    private void initBackButton() {
        backButton = ButtonFactory.createImageButton(assets.buttons.back);
        backButton.setPosition(20, GameConstants.HEIGHT - backButton.getHeight() - 20);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new QueueMapScreen(game));
            }
        });
    }

    private void initInfoButton() {
        infoButton = ButtonFactory.createImageButton(assets.buttons.book);
        infoButton.setPosition(340, GameConstants.HEIGHT - infoButton.getHeight() - 150);
        infoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InfoPage1(game));
            }
        });
        infoButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                infoDescBackground.setVisible(true);
                infoDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                infoDescBackground.setVisible(false);
                infoDescLabel.setVisible(false);
            }
        });
    }

    private void initInfoDescBackground() {
        Pixmap pixmap = new Pixmap(180, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(146/255.0f, 167/255.0f, 206/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(45/255.0f, 51/255.0f, 63/255.0f, 0.9f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        infoDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        infoDescBackground.setPosition(infoButton.getX() + infoButton.getWidth()*3/4,
                infoButton.getY() + infoButton.getHeight()/4);
        infoDescBackground.setVisible(false);
    }

    private void initInfoDescLabel() {
        String description = "Learn queue from \nHanon";
        infoDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        infoDescLabel.setPosition(infoDescBackground.getX() + infoDescBackground.getWidth()/2 - infoDescLabel.getWidth()/2,
                infoDescBackground.getY() + infoDescBackground.getHeight()/2 - infoDescLabel.getHeight()/2);
        infoDescLabel.setVisible(false);
    }

    private void initAppsButton() {
        appsButton = ButtonFactory.createImageButton(assets.buttons.apps);
        appsButton.setPosition(infoButton.getX() + infoButton.getWidth()/2 - appsButton.getWidth()/2,
                infoButton.getY() - appsButton.getHeight() - 20);
        appsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new ApplicationsScreen(game));
            }
        });
    }

    private void initSimulatorButton() {
        simulatorButton = ButtonFactory.createImageButton(assets.buttons.simulator);
        simulatorButton.setPosition(infoButton.getX() + infoButton.getWidth()/2 - simulatorButton.getWidth()/2,
                infoButton.getY() - simulatorButton.getHeight() - 40);
        simulatorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new QueueSimulatorScreen(game));
            }
        });
        simulatorButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                simulatorDescBackground.setVisible(true);
                simulatorDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                simulatorDescBackground.setVisible(false);
                simulatorDescLabel.setVisible(false);
            }
        });
    }

    private void initSimulatorDescBackground() {
        Pixmap pixmap = new Pixmap(180, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(146/255.0f, 167/255.0f, 206/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(45/255.0f, 51/255.0f, 63/255.0f, 0.9f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        simulatorDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        simulatorDescBackground.setPosition(simulatorButton.getX() + simulatorButton.getWidth()*3/4,
                simulatorButton.getY() + simulatorButton.getHeight()/4);
        simulatorDescBackground.setVisible(false);
    }

    private void initSimulatorDescLabel() {
        String description = "Try the queue \nsimulator";
        simulatorDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        simulatorDescLabel.setPosition(simulatorDescBackground.getX() + simulatorDescBackground.getWidth()/2 - simulatorDescLabel.getWidth()/2,
                simulatorDescBackground.getY() + simulatorDescBackground.getHeight()/2 - simulatorDescLabel.getHeight()/2);
        simulatorDescLabel.setVisible(false);
    }

    private void initInfoLabel() {
        infoLabel = LabelFactory.createLabel("QUEUE INFO", assets.fonts.verdana_30, QueueMapScreen.LABEL_COLOR);
        infoLabel.setPosition(infoButton.getX() + infoButton.getWidth() + 40,
                infoButton.getY() + infoButton.getHeight()/2 - infoLabel.getHeight()/2);
    }

    private void initAppsLabel() {
        appsLabel = LabelFactory.createLabel("APPLICATIONS", assets.fonts.verdana_30, QueueMapScreen.LABEL_COLOR);
        appsLabel.setPosition(infoLabel.getX(),
                appsButton.getY() + appsButton.getHeight()/2 - appsLabel.getHeight()/2);
    }

    private void initSimulatorLabel() {
        simulatorLabel = LabelFactory.createLabel("SIMULATOR", assets.fonts.verdana_30, QueueMapScreen.LABEL_COLOR);
        simulatorLabel.setPosition(appsLabel.getX(),
                simulatorButton.getY() + simulatorButton.getHeight()/2 - simulatorLabel.getHeight()/2);
    }

    private void initTeacherImage() {
        teacherImage = new Image(assets.images.old_guy);
        teacherImage.setPosition(GameConstants.WIDTH/4 - teacherImage.getWidth()/2 - 40,
                GameConstants.HEIGHT/2 - teacherImage.getHeight()/2 - 30);

    }
}
