package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.objects.Cloud;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of main menu
 */

public class TitleScreen extends AbstractoScreen {

    public static final String TAG = TitleScreen.class.getName();

    private Image backgroundImage;
    private Image treeImage;
    private Image titleImage;
    private Image maleCharacterImage;

    private ImageButton startButton;
    private ImageButton aboutButton;
    private List<Cloud> cloudList;

    public TitleScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTitleImage();
        initTreeImage();
        initMaleCharacterImage();
        initStartButton();
        initAboutButton();
        initClouds();
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        stage.addActor(backgroundImage);

        for (Cloud cloud: cloudList) {
            stage.addActor(cloud);
        }

        stage.addActor(treeImage);
        stage.addActor(startButton);
        stage.addActor(aboutButton);
        stage.addActor(maleCharacterImage);
        stage.addActor(titleImage);
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.grassland);
    }

    private void initTitleImage() {
        titleImage = new Image(assets.images.title_main);
        titleImage.setPosition(10, 10);
    }

    private void initTreeImage() {
        treeImage = new Image(assets.images.tree);
        treeImage.setPosition(275, 30);
    }

    private void initMaleCharacterImage() {
        maleCharacterImage = new Image(assets.images.male);
        maleCharacterImage.setPosition(GameConstants.WIDTH/2 - 50, treeImage.getY());
    }

    private void initStartButton() {
        startButton = ButtonFactory.createImageButton(assets.buttons.start);
        startButton.setPosition(GameConstants.WIDTH/2 + 110, GameConstants.HEIGHT - startButton.getHeight() - 160);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new WorldMapScreen(game));
            }
        });
    }

    private void initAboutButton() {
        aboutButton = ButtonFactory.createImageButton(assets.buttons.about);
        aboutButton.setPosition(GameConstants.WIDTH - aboutButton.getWidth() - 130, 55);
        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: 16/04/2017 About screen
            }
        });
    }

    private void initClouds() {
        cloudList = new ArrayList<>();
        cloudList.add(new Cloud(game, Cloud.TYPE_1));
        cloudList.add(new Cloud(game, Cloud.TYPE_2));
        cloudList.add(new Cloud(game, Cloud.TYPE_3));
    }

    @Override
    protected void backKeyPressed() {
        Gdx.app.exit();
    }



}
