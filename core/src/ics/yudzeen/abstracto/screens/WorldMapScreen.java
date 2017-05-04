package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
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
    private ImageButton queueKingdomButton;

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
        initQueueKingdomButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(mapImage);
        stage.addActor(queueKingdomButton);
        stage.addActor(stackNationButton);
        stage.addActor(titleImage);
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
        });
    }

    private void initQueueKingdomButton() {
        queueKingdomButton = ButtonFactory.createImageButton(assets.buttons.queue_region);
        queueKingdomButton.setPosition(393, 83);
        queueKingdomButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new QueueMapScreen(game));
            }
        });
    }


}
