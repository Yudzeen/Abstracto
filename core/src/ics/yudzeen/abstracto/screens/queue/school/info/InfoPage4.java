package ics.yudzeen.abstracto.screens.queue.school.info;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Info Page 4
 */

class InfoPage4 extends AbstractoScreen {

    static final String TAG = InfoPage4.class.getName();

    private static final int PAGE_NUMBER = 4;

    private Image backgroundImage;
    private Image teacherImage;
    private Label queueLabel;
    private Label pageLabel;
    private TextButton nextButton;
    private TextButton prevButton;

    private Label enqueueLabel;
    private Label descriptionLabel;
    private Image queueImage;
    private Image nodeImage1;
    private Image arrowImage;
    private Label headLabel;
    private Label tailLabel;

    InfoPage4(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initTeacherImage();
        initQueueLabel();
        initNextButton();
        initPageLabel();
        initPrevButton();

        initEnqueueLabel();
        initDescriptionLabel();
        initQueueImage();
        initNodeImage1();
        initArrowImage();
        initHeadLabel();
        initTailLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(teacherImage);
        stage.addActor(queueLabel);
        //stage.addActor(nextButton);
        stage.addActor(pageLabel);
        stage.addActor(prevButton);

        stage.addActor(enqueueLabel);
        stage.addActor(descriptionLabel);
        stage.addActor(queueImage);
        stage.addActor(arrowImage);
        stage.addActor(headLabel);
        stage.addActor(tailLabel);
        stage.addActor(nodeImage1);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new SchoolScreen(game));
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_blackboard);
    }

    private void initQueueLabel() {
        queueLabel = LabelFactory.createLabel("QUEUE", assets.fonts.chalk_40, Color.WHITE);
        queueLabel.setPosition(60, GameConstants.HEIGHT - queueLabel.getHeight() - 30);
    }

    private void initTeacherImage() {
        teacherImage = new Image(assets.images.old_guy);
        teacherImage.setPosition(100, 0 - teacherImage.getHeight()/2);
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
                game.setScreen(new InfoPage3(game));
            }
        });
    }

    private void initEnqueueLabel() {
        enqueueLabel = LabelFactory.createLabel("DEQUEUE", assets.fonts.chalk_30, Color.WHITE);
        enqueueLabel.setPosition(110, GameConstants.HEIGHT - enqueueLabel.getHeight() - 120);
    }

    private void initDescriptionLabel() {
        String text = "- deletes the head of the queue";
        descriptionLabel = LabelFactory.createLabel(text, assets.fonts.chalk_20, Color.WHITE);
        descriptionLabel.setPosition(enqueueLabel.getX() + 10, enqueueLabel.getY() - descriptionLabel.getHeight());
    }

    private void initQueueImage() {
        queueImage = new Image(assets.images.queue_chalk);
        queueImage.setPosition(GameConstants.WIDTH - queueImage.getWidth() - 170,
                120);
    }

    private void initNodeImage1() {
        nodeImage1 = new Image(assets.simulator.blank_node);
        nodeImage1.setPosition(queueImage.getX() + queueImage.getWidth() - nodeImage1.getWidth()*3/4,
                queueImage.getY() + queueImage.getHeight()/2 - nodeImage1.getHeight()/2);
    }

    private void initArrowImage() {
        arrowImage = new Image(assets.images.arrow_chalk);
        arrowImage.rotateBy(180);
        arrowImage.setPosition(queueImage.getX() + queueImage.getWidth()/2 - arrowImage.getWidth()/2 + 215,
                nodeImage1.getY() + 52);
    }

    private void initHeadLabel() {
        headLabel = LabelFactory.createLabel("HEAD", assets.fonts.chalk_20, Color.WHITE);
        headLabel.setPosition(queueImage.getX() + queueImage.getWidth() + 20,
                queueImage.getY() - headLabel.getHeight()/2);
    }

    private void initTailLabel() {
        tailLabel = LabelFactory.createLabel("TAIL", assets.fonts.chalk_20, Color.WHITE);
        tailLabel.setPosition(queueImage.getX() - 20 - tailLabel.getWidth(),
                queueImage.getY() + queueImage.getHeight()/2 - tailLabel.getHeight()/2);
    }
}
