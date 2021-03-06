package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.objects.Cloud;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Screen of main menu
 */

public class TitleScreen extends AbstractoScreen {

    public static final String TAG = TitleScreen.class.getName();

    private Image backgroundImage;
    private Image treeImage;
    private Image titleImage;
    private Image maleCharacterImage;
    private Image femaleCharacterImage;
    private Image oldGuyImage;
    private Image teacherImage;

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
        initFemaleCharacterImage();
        initOldGuyImage();
        initTeacherImage();
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
        stage.addActor(teacherImage);
        stage.addActor(maleCharacterImage);
        stage.addActor(femaleCharacterImage);
        stage.addActor(oldGuyImage);
        stage.addActor(titleImage);
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_grassland);
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

    private void initFemaleCharacterImage() {
        femaleCharacterImage = new Image(assets.images.female);
        femaleCharacterImage.setPosition(maleCharacterImage.getX() - femaleCharacterImage.getWidth() - 10,
                maleCharacterImage.getY() - 10);
    }

    private void initOldGuyImage() {
        oldGuyImage = new Image(assets.images.old_guy);
        oldGuyImage.setPosition(50, maleCharacterImage.getY());
    }

    private void initTeacherImage() {
        teacherImage = new Image(assets.images.teacher);
        teacherImage.setPosition(oldGuyImage.getRight() + 70, oldGuyImage.getY());
    }

    private void initStartButton() {
        startButton = ButtonFactory.createImageButton(assets.buttons.start);
        startButton.setPosition(GameConstants.WIDTH/2 + 110, GameConstants.HEIGHT - startButton.getHeight() - 160);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(fadeOut(1.0f, Interpolation.fade));
                sequenceAction.addAction(run(new Runnable() {
                    @Override
                    public void run() {
                        if(!getGame().getGamePreferences().introDone) {
                            game.setScreen(new ChooseCharacterScreen(game));
                        }
                        else {
                            game.setScreen(new WorldMapScreen(game));
                        }
                    }
                }));
                stage.addAction(sequenceAction);
            }
        });
    }

    private void initAboutButton() {
        aboutButton = ButtonFactory.createImageButton(assets.buttons.about);
        aboutButton.setPosition(GameConstants.WIDTH - aboutButton.getWidth() - 35, 80);
        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new AboutScreen(game));
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
