package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Introduction
 */

public class IntroductionScreen extends AbstractoScreen {

    public static final String TAG = IntroductionScreen.class.getName();

    private Image backgroundImage;
    private Label textLabel;

    private Image triangle;

    private ActorGestureListener gestureListener;

    private List<String> dialogue;

    private int dialogueIndex = 0;

    public IntroductionScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initDialogue();
        initBackgroundImage();
        initTextLabel();
        initTriangleButton();
        initGestureListener();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(textLabel);
        stage.addActor(triangle);

        stage.addListener(gestureListener);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new TitleScreen(game));
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_introduction);
    }

    private void initTextLabel() {
        textLabel = LabelFactory.createLabel("Welcome!", assets.fonts.verdana_30, Color.BLACK);
        textLabel.setPosition(368 + 430/2 - textLabel.getWidth()/2, 150 + 183/2 - textLabel.getHeight()/2);
    }

    private void initTriangleButton() {
        triangle = new Image(assets.buttons.blue_triangle) {
            boolean forward = true;

            @Override
            public void act(float delta) {
                if(forward) {
                    if(getX() < 768) {
                        setX(getX() + delta*50);
                    }
                    else {
                        forward = false;
                    }
                }
                else {
                    if(getX() > 758) {
                        setX(getX() - delta*50);
                    }
                    else {
                        forward = true;
                    }
                }

            }
        };
        triangle.setPosition(763, 160);
    }

    private void initGestureListener() {
        gestureListener = new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if(dialogueIndex == dialogue.size()) {
                    GamePreferences gamePreferences = getGame().getGamePreferences();
                    gamePreferences.introDone = true;
                    gamePreferences.save();
                    game.setScreen(new WorldMapScreen(game));
                }
                else {
                    textLabel.setText(dialogue.get(dialogueIndex));
                    textLabel.pack();
                    textLabel.setPosition(368 + 430/2 - textLabel.getWidth()/2, 150 + 183/2 - textLabel.getHeight()/2);
                    dialogueIndex++;
                }
            }
        };
    }

    private void initDialogue() {
        dialogue = new ArrayList<>();
        dialogue.add("So you are " + game.getGamePreferences().name + "!");
        dialogue.add("I am Larxene, a \nprofessor in the \nworld of Abstracto.");
        dialogue.add("In this world, you will \nlearn different \nabstract data types.");
        dialogue.add("Abstract data types are \nimportant for your \nprogramming adventures.");
        dialogue.add("Explore the world of \nAbstracto and have fun!");
    }
}
