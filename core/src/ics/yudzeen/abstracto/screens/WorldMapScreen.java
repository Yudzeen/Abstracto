package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Screen of world map
 */

public class WorldMapScreen extends AbstractoScreen {

    public WorldMapScreen(Abstracto game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildBackground();
        buildTitle();
        buildMapButtons();
    }

    private void buildTitle() {
        Image title = new Image(assets.images.title_map);
        title.setPosition(GameConstants.WIDTH/2 - title.getWidth()/2, 5);
        stage.addActor(title);
    }

    private void buildBackground() {
        Image backgroundImage = new Image(assets.images.blank_map);
        backgroundImage.setWidth(GameConstants.WIDTH);
        backgroundImage.setPosition(GameConstants.WIDTH/2 - backgroundImage.getWidth()/2, GameConstants.HEIGHT/2 - backgroundImage.getHeight()/2);
        stage.addActor(backgroundImage);
    }

    private void buildMapButtons() {
        ImageButton homeMapButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        homeMapButton.setPosition(GameConstants.WIDTH/2 - homeMapButton.getWidth()/2 - 30, GameConstants.HEIGHT/2 - homeMapButton.getHeight()/2);
        homeMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HomeScreen(game));
            }
        });
        stage.addActor(homeMapButton);
        Label homeLabel = new Label("Home", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        homeLabel.setPosition(homeMapButton.getX() + homeMapButton.getWidth()/2 - homeLabel.getWidth()/2, homeMapButton.getY() - 15);
        stage.addActor(homeLabel);

        ImageButton stackMapButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        stackMapButton.setPosition(110, GameConstants.HEIGHT - 120);
        stackMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new StackMapScreen(game));
            }
        });
        stage.addActor(stackMapButton);
        Label stackMapLabel = new Label("Stackville", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        stackMapLabel.setPosition(stackMapButton.getX() + stackMapButton.getWidth()/2 - stackMapLabel.getWidth()/2, stackMapButton.getY() - 15);
        stage.addActor(stackMapLabel);

        ImageButton queueMapButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        queueMapButton.setPosition(150, 100);
        queueMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new QueueMapScreen(game));
            }
        });
        stage.addActor(queueMapButton);
        Label queueMapLabel = new Label("Queue City", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        queueMapLabel.setPosition(queueMapButton.getX() + queueMapButton.getWidth()/2 - queueMapLabel.getWidth()/2, queueMapButton.getY() - 15);
        stage.addActor(queueMapLabel);

        ImageButton graphMapButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        graphMapButton.setPosition(GameConstants.WIDTH/2 + 185, GameConstants.HEIGHT/2 + 30);
        queueMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: graph screen
            }
        });
        // TODO: Remove Later
        graphMapButton.setDisabled(true);
        stage.addActor(graphMapButton);
        Label graphMapLabel = new Label("Graphopolis", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        graphMapLabel.setPosition(graphMapButton.getX() + graphMapButton.getWidth()/2 - graphMapLabel.getWidth()/2, graphMapButton.getY() - 15);
        stage.addActor(graphMapLabel);


        ImageButton treeMapButton = ButtonFactory.createImageButton(assets.buttons.town_icon);
        treeMapButton.setPosition(GameConstants.WIDTH/2 + 210, GameConstants.HEIGHT/2 - 140);
        treeMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: tree screen
            }
        });
        // TODO: Remove Later
        treeMapButton.setDisabled(true);
        stage.addActor(treeMapButton);
        Label treeMapLabel = new Label("Tree Town", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        treeMapLabel.setPosition(treeMapButton.getX() + treeMapButton.getWidth()/2 - treeMapLabel.getWidth()/2, treeMapButton.getY() - 15);
        stage.addActor(treeMapLabel);
    }
}
