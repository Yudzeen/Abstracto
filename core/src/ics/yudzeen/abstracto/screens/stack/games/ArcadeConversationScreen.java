package ics.yudzeen.abstracto.screens.stack.games;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.GamePreferences;

/**
 * Arcade conversation screen
 */

public class ArcadeConversationScreen extends AbstractoScreen {

    public static final String TAG = ArcadeConversationScreen.class.getName();

    private Image backgroundImage;
    private Image personImage;

    private Image triangle;

    private Image chatBubble;
    private Label chatLabel;

    private ActorGestureListener gestureListener;

    private List<String> dialogue;
    private int dialogueIndex = 0;

    public ArcadeConversationScreen(Abstracto game) {
        super(game);
        init();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(personImage);
        stage.addActor(chatBubble);
        stage.addActor(chatLabel);
        stage.addActor(triangle);
        stage.addListener(gestureListener);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }

    private void init() {
        initBackgroundImage();
        initPersonImage();
        initChatBubbleImage();
        initTextLabel();
        initTriangleButton();
        initDialogue();
        initGestureListener();
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(66/255.0f, 76/255.0f, 59/255.0f, 1.0f));
        pixmap.fill();

        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initPersonImage() {
        GamePreferences gamePreferences = game.getGamePreferences();
        gamePreferences.load();
        personImage = new Image(gamePreferences.character.equals("MALE") ? assets.images.female : assets.images.male);
        personImage.setPosition(150, 0 - personImage.getHeight()/2);
    }

    private void initChatBubbleImage() {
        chatBubble = new Image(assets.images.chat_bubble);
        chatBubble.setPosition(345, 90);
    }

    private void initTriangleButton() {
        triangle = new Image(assets.buttons.blue_triangle) {
            boolean forward = true;

            @Override
            public void act(float delta) {
                if(forward) {
                    if(getX() < 640) {
                        setX(getX() + delta*50);
                    }
                    else {
                        forward = false;
                    }
                }
                else {
                    if(getX() > 630) {
                        setX(getX() - delta*50);
                    }
                    else {
                        forward = true;
                    }
                }

            }
        };
        triangle.setPosition(635, 170);
    }

    private void initTextLabel() {
        chatLabel = LabelFactory.createLabel("Hi there!", assets.fonts.verdana_30, Color.BLACK);
        chatLabel.setPosition(350 + 325/2 - chatLabel.getWidth()/2, 155 + 180/2 - chatLabel.getHeight()/2);
    }

    private void initGestureListener() {
        gestureListener = new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if(dialogueIndex == dialogue.size()) {
                    GamePreferences gamePreferences = game.getGamePreferences();
                    gamePreferences.stackArenaDialogueDone = true;
                    gamePreferences.save();
                    game.setScreen(new ArcadeMapScreen(game));
                }
                else {
                    chatLabel.setText(dialogue.get(dialogueIndex));
                    chatLabel.pack();
                    chatLabel.setPosition(350 + 325/2 - chatLabel.getWidth()/2, 155 + 175/2 - chatLabel.getHeight()/2);
                    dialogueIndex++;
                }
            }
        };
    }

    private void initDialogue() {
        dialogue = new ArrayList<>();
        GamePreferences gamePreferences = game.getGamePreferences();
        gamePreferences.load();
        dialogue.add("I am " + (gamePreferences.character.equals("MALE") ? "Whitney" : "Steven") + ".");
        dialogue.add("I always play \nhere in the \narcade.");
        dialogue.add("Let's play together!");
    }
}
