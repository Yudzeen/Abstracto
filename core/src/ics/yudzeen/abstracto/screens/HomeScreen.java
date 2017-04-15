package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of home
 */

public class HomeScreen extends AbstractoScreen {

    public static final String TAG = HomeScreen.class.getName();

    private Image backgroundImage;
    private Image locationLabel;
    private Image characterImage;

    private ImageButton worldMapButton;
    private ImageButton profileButton;
    private ImageButton achievementsButton;

    public HomeScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initLocationLabel();
        initCharacterImage();
        initWorldMapButton();
        initProfileButton();
        initAchievementsButton();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(locationLabel);
        stage.addActor(characterImage);
        stage.addActor(worldMapButton);
        stage.addActor(profileButton);
        stage.addActor(achievementsButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new TitleScreen(game));
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
        locationLabel = new Image(assets.images.location_home);
        locationLabel.setPosition(0, GameConstants.HEIGHT - locationLabel.getHeight());
    }

    private void initCharacterImage() {
        characterImage = new Image(assets.images.character);
        characterImage.setPosition(150, 0);
    }

    private void initWorldMapButton() {
        worldMapButton = ButtonFactory.createImageButton(assets.buttons.map);
        worldMapButton.setPosition(GameConstants.WIDTH - worldMapButton.getWidth() - 10,
                GameConstants.HEIGHT - worldMapButton.getHeight() - 10);
        worldMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new WorldMapScreen(game));
            }
        });
    }

    private void initProfileButton() {
        profileButton = ButtonFactory.createImageButton(assets.buttons.profile);
        profileButton.setPosition(GameConstants.WIDTH/2 + 10, GameConstants.HEIGHT/2 + 30);
        profileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: my profile
            }
        });
    }

    private void initAchievementsButton() {
        achievementsButton = ButtonFactory.createImageButton(assets.buttons.achievements);
        achievementsButton.setPosition(profileButton.getX() + profileButton.getWidth()/2 - achievementsButton.getWidth()/2,
                profileButton.getY() - profileButton.getHeight() - 20);
        achievementsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: achievements
            }
        });
    }

}
