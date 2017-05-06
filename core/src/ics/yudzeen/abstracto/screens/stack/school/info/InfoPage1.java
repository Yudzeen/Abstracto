package ics.yudzeen.abstracto.screens.stack.school.info;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.screens.stack.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Info Page 1
 */

class InfoPage1 extends AbstractoScreen {

    static final String TAG = InfoPage1.class.getName();
    private static final int PAGE_NUMBER = 1;

    private Image backgroundImage;
    private Image teacherImage;
    private Image stackImage;

    private Label stackLabel;
    private Label descriptionLabel;
    private Label topofstackLabel;

    private TextButton nextButton;
    private TextButton prevButton;

    private List<Image> nodeImages;
    private Image arrowImage;

    private Label pageLabel;

    InfoPage1(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initStackLabel();
        initTeacherImage();
        initNextButton();
        initPageLabel();
        initPrevButton();
        initDescriptionLabel();
        initStackImage();
        initNodeImages();
        initArrowImage();
        initTopofstackLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(teacherImage);
        stage.addActor(stackLabel);
        stage.addActor(nextButton);
        stage.addActor(pageLabel);
        //stage.addActor(prevButton);
        stage.addActor(descriptionLabel);
        stage.addActor(stackImage);
        for (Image node : nodeImages) {
            stage.addActor(node);
        }
        stage.addActor(arrowImage);
        stage.addActor(topofstackLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new SchoolScreen(game));
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_blackboard);
    }

    private void initStackLabel() {
        stackLabel = LabelFactory.createLabel("STACK", assets.fonts.chalk_40, Color.WHITE);
        stackLabel.setPosition(60, GameConstants.HEIGHT - stackLabel.getHeight() - 30);
    }

    private void initTeacherImage() {
        teacherImage = new Image(assets.images.teacher);
        teacherImage.setPosition(50, 0 - teacherImage.getHeight()/2);
    }

    private void initNextButton() {
        nextButton = ButtonFactory.createTextButton("NEXT", 20, 20, new Color(14/255.0f, 69/255.0f, 31/255.0f, 1), Color.WHITE, assets.fonts.chalk_20);
        nextButton.setPosition(GameConstants.WIDTH - nextButton.getWidth() - 40,
                50);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InfoPage2(game));
            }
        });
    }

    private void initPageLabel() {
        pageLabel = LabelFactory.createLabel(PAGE_NUMBER + "/" + InfoConstants.TOTAL_PAGES, assets.fonts.chalk_20, Color.WHITE);
        pageLabel.setPosition(nextButton.getX() - pageLabel.getWidth() - 20, nextButton.getY());
    }

    private void initPrevButton() {
        prevButton = ButtonFactory.createTextButton("BACK", 20, 20, new Color(14/255.0f, 69/255.0f, 31/255.0f, 1), Color.WHITE, assets.fonts.chalk_20);
        prevButton.setPosition(pageLabel.getX() - prevButton.getWidth() - 20, pageLabel.getY());
        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InfoPage1(game));
            }
        });
    }

    private void initDescriptionLabel() {
        String text = "- a structure where manipulations \n" +
                "  can only be performed in one\n" +
                "  position called top of stack";
        descriptionLabel = LabelFactory.createLabel(text, assets.fonts.chalk_20, Color.WHITE);
        descriptionLabel.setPosition(110, GameConstants.HEIGHT - descriptionLabel.getHeight() - 120);
    }

    private void initStackImage() {
        stackImage = new Image(assets.images.stack_chalk);
        stackImage.setPosition(GameConstants.WIDTH - stackImage.getWidth() - 145,
                GameConstants.HEIGHT - stackImage.getHeight() - 150);
    }

    private void initNodeImages() {
        nodeImages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Image node = new Image(assets.simulator.blank_node);
            node.setPosition(stackImage.getX() + stackImage.getWidth()/2 - node.getWidth()/2,
                    stackImage.getY() + 10 + i*node.getWidth());
            nodeImages.add(node);
        }
    }

    private void initArrowImage() {
        arrowImage = new Image(assets.images.arrow_chalk);
        arrowImage.setPosition(stackImage.getX() + stackImage.getWidth() + 10,
                stackImage.getY() + stackImage.getHeight() - 80);
    }

    private void initTopofstackLabel() {
        String text = "TOP OF \n STACK";
        topofstackLabel = LabelFactory.createLabel(text, assets.fonts.chalk_20, Color.WHITE);
        topofstackLabel.setPosition(arrowImage.getX(), arrowImage.getY() - topofstackLabel.getHeight() - 5);
    }
}
