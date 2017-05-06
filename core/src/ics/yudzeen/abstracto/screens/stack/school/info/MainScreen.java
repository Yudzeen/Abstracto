package ics.yudzeen.abstracto.screens.stack.school.info;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Info Main Screen
 */

public class MainScreen extends AbstractoScreen {

    static final String TAG = MainScreen.class.getName();

    private Image backgroundImage;
    private Image teacherImage;

    private Image triangle;

    private Image chatBubble;
    private Label chatLabel;

    private ActorGestureListener gestureListener;

    private List<String> dialogue;
    private int dialogueIndex = 0;

    private TextButton skipButton;

    public MainScreen(Abstracto game) {
        super(game);
        init();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(teacherImage);
        stage.addActor(chatBubble);
        stage.addActor(chatLabel);
        stage.addActor(triangle);
        stage.addListener(gestureListener);
        stage.addActor(skipButton);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new SchoolScreen(game));
    }

    private void init() {
        initBackgroundImage();
        initTeacherImage();
        initChatBubbleImage();
        initTextLabel();
        initTriangleButton();
        initDialogue();
        initGestureListener();
        initSkipButton();
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_blackboard);
    }

    private void initTeacherImage() {
        teacherImage = new Image(assets.images.teacher);
        teacherImage.setPosition(150, 0 - teacherImage.getHeight()/2);
    }

    private void initChatBubbleImage() {
        chatBubble = new Image(assets.images.chat_bubble);
        chatBubble.setPosition(teacherImage.getX() + teacherImage.getWidth() + 20,
                teacherImage.getY() + teacherImage.getHeight()*3/4);
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
        chatLabel = LabelFactory.createLabel("Hello there!", assets.fonts.verdana_30, Color.BLACK);
        chatLabel.setPosition(350 + 325/2 - chatLabel.getWidth()/2, 155 + 180/2 - chatLabel.getHeight()/2);
    }

    private void initGestureListener() {
        gestureListener = new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                if(dialogueIndex == dialogue.size()) {
                    game.setScreen(new InfoPage1(game));
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
        dialogue.add(game.getGamePreferences().name + " isn't it?");
        dialogue.add("I am Larxene, in \ncase you have \nforgotten.");
        dialogue.add("I can teach you \nabout stack.");
        dialogue.add("Let's begin!");
    }

    private void initSkipButton() {
        skipButton = ButtonFactory.createTextButton("SKIP", 20, 20, new Color(14/255.0f, 69/255.0f, 31/255.0f, 1), Color.WHITE, assets.fonts.chalk_20);
        skipButton.setPosition(GameConstants.WIDTH - skipButton.getWidth() - 40,
                50);
        skipButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InfoPage1(game));
            }
        });
    }
}
