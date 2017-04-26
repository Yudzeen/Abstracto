package ics.yudzeen.abstracto.screens.stack.school.info;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Page 3
 */

class InfoPage3 extends AbstractoScreen {

    static final String TAG = InfoPage3.class.getName();

    private static final int PAGE_NUMBER = 3;

    private Image backgroundImage;
    private Image teacherImage;

    private Label stackLabel;

    private TextButton nextButton;
    private TextButton prevButton;

    private Label pageLabel;

    private Label pushLabel;
    private Label descriptionLabel;
    private Image stackImage;
    private Image nodeImage1;
    private Image arrowImage;

    public InfoPage3(Abstracto game) {
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

        initPushLabel();
        initDescriptionLabel();
        initStackImage();
        initNodeImage1();
        initArrowImage();
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

        stage.addActor(pushLabel);
        stage.addActor(descriptionLabel);
        stage.addActor(stackImage);
        stage.addActor(nodeImage1);
        stage.addActor(arrowImage);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
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
                game.setScreen(new InfoPage4(game));
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
                game.setScreen(new InfoPage2(game));
            }
        });
    }

    private void initPushLabel() {
        pushLabel = LabelFactory.createLabel("PUSH", assets.fonts.chalk_30, Color.WHITE);
        pushLabel.setPosition(110, GameConstants.HEIGHT - pushLabel.getHeight() - 120);
    }

    private void initDescriptionLabel() {
        String text = "- inserts at the top of \n stack";
        descriptionLabel = LabelFactory.createLabel(text, assets.fonts.chalk_20, Color.WHITE);
        descriptionLabel.setPosition(pushLabel.getX() + 10, pushLabel.getY() - descriptionLabel.getHeight());
    }

    private void initStackImage() {
        stackImage = new Image(assets.images.stack_chalk);
        stackImage.setPosition(GameConstants.WIDTH - stackImage.getWidth() - 170,
                120);
    }

    private void initNodeImage1() {
        nodeImage1 = new Image(assets.simulator.blank_node);
        nodeImage1.setPosition(stackImage.getX() + stackImage.getWidth()/2 - nodeImage1.getWidth()/2,
                stackImage.getY() + stackImage.getHeight() - nodeImage1.getHeight()/2);
    }

    private void initArrowImage() {
        arrowImage = new Image(assets.images.arrow_chalk);
        arrowImage.rotateBy(90);
        arrowImage.setPosition(stackImage.getX() + stackImage.getWidth()/2 - arrowImage.getWidth()/2 + 63,
                nodeImage1.getY() - arrowImage.getHeight() - 40);
    }
}
