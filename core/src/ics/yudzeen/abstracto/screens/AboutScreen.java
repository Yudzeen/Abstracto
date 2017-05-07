package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.objects.Cloud;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * About Screen
 */

public class AboutScreen extends AbstractoScreen {

    public static final String TAG = AboutScreen.class.getName();

    private Image backgroundImage;
    private Image treeImage;
    private List<Cloud> cloudList;
    private Image panelImage;
    private Label panelTitle;
    private Label panelSubtitle;
    private Label panelText;
    private Label panelFooter;

    public AboutScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        initBackgroundImage();
        initClouds();
        initTreeImage();
        initPanelImage();
        initPanelTitle();
        initPanelSubtitle();
        initPanelText();
        initPanelFooter();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        for (Cloud cloud : cloudList) {
            stage.addActor(cloud);
        }
        stage.addActor(treeImage);
        stage.addActor(panelImage);
        stage.addActor(panelTitle);
        stage.addActor(panelSubtitle);
        stage.addActor(panelText);
        stage.addActor(panelFooter);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new TitleScreen(game));
    }

    private void initBackgroundImage() {
        backgroundImage = new Image(assets.images.background_grassland);
    }

    private void initClouds() {
        cloudList = new ArrayList<>();
        cloudList.add(new Cloud(game, Cloud.TYPE_1));
        cloudList.add(new Cloud(game, Cloud.TYPE_2));
        cloudList.add(new Cloud(game, Cloud.TYPE_3));
    }

    private void initTreeImage() {
        treeImage = new Image(assets.images.tree);
        treeImage.setPosition(275, 30);
    }

    private void initPanelImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH*2/3, GameConstants.HEIGHT*2/3, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(146/255.0f, 167/255.0f, 206/255.0f, 0.99f));
        pixmap.fill();
        pixmap.setColor(new Color(45/255.0f, 51/255.0f, 63/255.0f, 0.99f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        panelImage = new Image(new Texture(pixmap));
        pixmap.dispose();

        panelImage.setPosition(GameConstants.WIDTH/2 - panelImage.getWidth()/2,
                GameConstants.HEIGHT/2 - panelImage.getHeight()/2);
    }

    private void initPanelTitle() {
        panelTitle = LabelFactory.createLabel("ABSTRACTO", assets.fonts.verdana_40, Color.BLACK);
        panelTitle.setPosition(panelImage.getX() + panelImage.getWidth()/2 - panelTitle.getWidth()/2,
                panelImage.getTop() - 30 - panelTitle.getHeight());
    }

    private void initPanelSubtitle() {
        String text = "A Mobile Educational Game For Learning \n" +
                "               Abstract Data Types";
        panelSubtitle = LabelFactory.createLabel(text, assets.fonts.verdana_20);
        panelSubtitle.setPosition(panelImage.getX() + panelImage.getWidth()/2 - panelSubtitle.getWidth()/2,
                panelTitle.getY() - 15 - panelSubtitle.getHeight());
    }

    private void initPanelText() {
        String text = "Eugene B. Javiñas and Caroline Natalie M. Peralta";
        panelText = LabelFactory.createLabel(text, assets.fonts.verdana_16);
        panelText.setPosition(panelImage.getX() + panelImage.getWidth()/2 - panelText.getWidth()/2,
                panelSubtitle.getY() - 30 - panelText.getHeight());
    }

    private void initPanelFooter() {
        String text = "      CMSC 190 SPECIAL PROBLEM\n" +
                "  INSTITUTE OF COMPUTER SCIENCE";
        panelFooter = LabelFactory.createLabel(text, assets.fonts.verdana_20);
        panelFooter.setPosition(panelImage.getX() + panelImage.getWidth()/2 - panelFooter.getWidth()/2,
                panelImage.getY() + 20);
    }
}
