package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of world map
 */

public class WorldMapScreen extends AbstractoScreen {

    public static final String TAG = WorldMapScreen.class.getName();

    private Image backgroundImage;
    private Image mapImage;
    private Image titleImage;

    private ImageButton homeButton;
    private Label homeLabel;

    private ImageButton stackvilleButton;
    private Label stackvilleLabel;

    private ImageButton queuecityButton;
    private Label queuecityLabel;

    private ImageButton graphopolisButton;
    private Label graphopolisLabel;

    private ImageButton treetownButton;
    private Label treetownLabel;

    public WorldMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleImage();
        initTitleImage();
        initMapImage();
        initHomeButton();
        initHomeLabel();
        initStackvilleButton();
        initStackvilleLabel();
        initQueuecityButton();
        initQueuecityLabel();
        initGraphopolisButton();
        initGraphopolisLabel();
        initTreetownButton();
        initTreetownLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(mapImage);
        stage.addActor(titleImage);
        stage.addActor(homeButton);
        stage.addActor(homeLabel);
        stage.addActor(stackvilleButton);
        stage.addActor(stackvilleLabel);
        stage.addActor(queuecityButton);
        stage.addActor(queuecityLabel);

        graphopolisButton.setDisabled(true);
        stage.addActor(graphopolisButton);
        stage.addActor(graphopolisLabel);

        treetownButton.setDisabled(true);
        stage.addActor(treetownButton);
        stage.addActor(treetownLabel);
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        pixmap.setColor(0,0,0,1);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initTitleImage() {
        titleImage = new Image(assets.images.title_map);
        titleImage.setPosition(GameConstants.WIDTH/2 - titleImage.getWidth()/2, 5);
    }

    private void initMapImage() {
        mapImage = new Image(assets.images.blank_map);
        mapImage.setWidth(GameConstants.WIDTH);
        mapImage.setPosition(GameConstants.WIDTH/2 - mapImage.getWidth()/2, GameConstants.HEIGHT/2 - mapImage.getHeight()/2);
    }

    private void initHomeButton() {
        homeButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        homeButton.setPosition(GameConstants.WIDTH/2 - homeButton.getWidth()/2 - 30, GameConstants.HEIGHT/2 - homeButton.getHeight()/2);
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game));
            }
        });
    }

    private void initHomeLabel() {
        homeLabel = LabelFactory.createLabel("Home", Color.DARK_GRAY);
        homeLabel.setPosition(homeButton.getX() + (homeButton.getWidth()-homeLabel.getWidth())/2,
                homeButton.getY()-15);
    }

    private void initStackvilleButton() {
        stackvilleButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        stackvilleButton.setPosition(110, GameConstants.HEIGHT - 120);
        stackvilleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StackMapScreen(game));
            }
        });
    }

    private void initStackvilleLabel() {
        stackvilleLabel = LabelFactory.createLabel("Stackville", Color.DARK_GRAY);
        stackvilleLabel.setPosition(stackvilleButton.getX() + (stackvilleButton.getWidth()- stackvilleLabel.getWidth())/2,
                stackvilleButton.getY() - 15);
    }

    private void initQueuecityButton() {
        queuecityButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        queuecityButton.setPosition(150, 100);
        queuecityButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new QueueMapScreen(game));
            }
        });
    }

    private void initQueuecityLabel() {
        queuecityLabel = LabelFactory.createLabel("Queue City", Color.DARK_GRAY);
        queuecityLabel.setPosition(queuecityButton.getX() + (queuecityButton.getWidth()-queuecityLabel.getWidth())/2,
                queuecityButton.getY() - 15);
    }

    private void initGraphopolisButton() {
        graphopolisButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        graphopolisButton.setPosition(GameConstants.WIDTH/2 + 185, GameConstants.HEIGHT/2 + 30);
        graphopolisButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 Graph screen
            }
        });
    }

    private void initGraphopolisLabel() {
        graphopolisLabel = LabelFactory.createLabel("Graphopolis", Color.DARK_GRAY);
        graphopolisLabel.setPosition(graphopolisButton.getX() + (graphopolisButton.getWidth() - graphopolisLabel.getWidth())/2,
                graphopolisButton.getY() - 15);
    }

    private void initTreetownButton() {
        treetownButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        treetownButton.setPosition(GameConstants.WIDTH/2 + 210, GameConstants.HEIGHT/2 - 140);
        treetownButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 14/04/2017 Tree screen
            }
        });
    }

    private void initTreetownLabel() {
        treetownLabel = LabelFactory.createLabel("Tree Town", Color.DARK_GRAY);
        treetownLabel.setPosition(treetownButton.getX() + (treetownButton.getWidth() - treetownLabel.getWidth())/2,
                treetownButton.getY() - 15);
    }

}
