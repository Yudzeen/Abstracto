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
 * Page 2
 */

class InfoPage2 extends AbstractoScreen {

    static final String TAG = InfoPage2.class.getName();
    private static final int PAGE_NUMBER = 2;

    private Image backgroundImage;
    private Image teacherImage;

    private Label stackLabel;

    private TextButton nextButton;
    private TextButton prevButton;

    private Label operationsLabel;
    private Label descriptionLabel;

    private Label pageLabel;

    InfoPage2(Abstracto game) {
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
        initOperationsLabel();
        initDescriptionLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(teacherImage);
        stage.addActor(stackLabel);
        stage.addActor(nextButton);
        stage.addActor(pageLabel);
        stage.addActor(prevButton);

        stage.addActor(operationsLabel);
        stage.addActor(descriptionLabel);
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
                game.setScreen(new InfoPage3(game));
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

    private void initOperationsLabel() {
        operationsLabel = LabelFactory.createLabel("OPERATIONS", assets.fonts.chalk_30, Color.WHITE);
        operationsLabel.setPosition(110, GameConstants.HEIGHT - operationsLabel.getHeight() - 120);
    }

    private void initDescriptionLabel() {
        String text = " 1. Push \n 2. Pop";
        descriptionLabel = LabelFactory.createLabel(text, assets.fonts.chalk_20, Color.WHITE);
        descriptionLabel.setPosition(135, operationsLabel.getY() - descriptionLabel.getHeight() - 10);
    }


}
