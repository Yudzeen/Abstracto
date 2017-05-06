package ics.yudzeen.abstracto.screens;

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

    private ImageButton stackNationButton;
    private Image stackDescBackground;
    private Label stackDescLabel;

    private ImageButton queueKingdomButton;
    private Image queueDescBackground;
    private Label queueDescLabel;


    public WorldMapScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleImage();
        initTitleImage();
        initMapImage();

        initStackNationButton();
        initStackDescBackground();
        initStackDescLabel();

        initQueueKingdomButton();
        initQueueDescBackground();
        initQueueDescLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(mapImage);
        stage.addActor(stackNationButton);
        stage.addActor(queueKingdomButton);
        stage.addActor(titleImage);
        stage.addActor(stackDescBackground);
        stage.addActor(stackDescLabel);
        stage.addActor(queueDescBackground);
        stage.addActor(queueDescLabel);
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(74/255.0f,143/255.0f,231/255.0f,1);
        //pixmap.setColor(0,0,0,1);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        backgroundImage.setPosition(0,0);
        pixmap.dispose();
    }

    private void initTitleImage() {
        titleImage = new Image(assets.images.world_map_title);
        titleImage.setPosition(GameConstants.WIDTH/2 - titleImage.getWidth()/2, 5);
    }

    private void initMapImage() {
        mapImage = new Image(assets.images.region_map);
    }

    private void initStackNationButton() {
        stackNationButton = ButtonFactory.createImageButton(assets.buttons.stack_region);
        stackNationButton.setPosition(275,141);
        stackNationButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StackMapScreen(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                stackDescBackground.setVisible(true);
                stackDescLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                stackDescBackground.setVisible(false);
                stackDescLabel.setVisible(false);
            }
        });
        stackNationButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stackDescBackground.setVisible(true);
                stackDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stackDescBackground.setVisible(false);
                stackDescLabel.setVisible(false);
            }
        });
    }

    private void initStackDescBackground() {
        Pixmap pixmap = new Pixmap(170, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(188/255.0f, 155/255.0f, 115/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(new Color(117/255.0f, 96/255.0f, 72/255.0f, 0.9f)));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        stackDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        stackDescBackground.setPosition(358, 300);
        stackDescBackground.setVisible(false);
    }

    private void initStackDescLabel() {
        String description = "People here are \nmasters of stack";
        stackDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        stackDescLabel.setPosition(stackDescBackground.getX() + stackDescBackground.getWidth()/2 - stackDescLabel.getWidth()/2,
                stackDescBackground.getY() + stackDescBackground.getHeight()/2 - stackDescLabel.getHeight()/2);
        stackDescLabel.setVisible(false);
    }

    private void initQueueKingdomButton() {
        queueKingdomButton = ButtonFactory.createImageButton(assets.buttons.queue_region);
        queueKingdomButton.setPosition(393, 83);
        queueKingdomButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new QueueMapScreen(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                queueDescBackground.setVisible(true);
                queueDescLabel.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                queueDescBackground.setVisible(false);
                queueDescLabel.setVisible(false);
            }
        });
        queueKingdomButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                queueDescBackground.setVisible(true);
                queueDescLabel.setVisible(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                queueDescBackground.setVisible(false);
                queueDescLabel.setVisible(false);
            }
        });
    }

    private void initQueueDescBackground() {
        Pixmap pixmap = new Pixmap(170, 70, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(188/255.0f, 155/255.0f, 115/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(new Color(117/255.0f, 96/255.0f, 72/255.0f, 0.9f)));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        queueDescBackground = new Image(new Texture(pixmap));
        pixmap.dispose();

        queueDescBackground.setPosition(510, 200);
        queueDescBackground.setVisible(false);
    }

    private void initQueueDescLabel() {
        String description = "People here are \nqueue experts";
        queueDescLabel = LabelFactory.createLabel(description, assets.fonts.verdana_16, Color.BLACK);
        queueDescLabel.setPosition(queueDescBackground.getX() + queueDescBackground.getWidth()/2 - queueDescLabel.getWidth()/2,
                queueDescBackground.getY() + queueDescBackground.getHeight()/2 - queueDescLabel.getHeight()/2);
        queueDescLabel.setVisible(false);
    }




}
