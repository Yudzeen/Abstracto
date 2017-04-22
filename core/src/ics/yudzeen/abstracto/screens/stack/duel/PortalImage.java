package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * An image for the portal
 */

class PortalImage extends Actor {

    static final String TAG = PortalImage.class.getName();

    private DuelScreen gameScreen;
    private TextureAtlas.AtlasRegion texture;

    private Label label;
    private String text;

    PortalImage(DuelScreen gameScreen, String text) {
        this.gameScreen = gameScreen;
        this.text = text;
        init();
    }

    PortalImage(DuelScreen gameScreen) {
        this(gameScreen, "");
    }

    private void init() {
        texture = gameScreen.getAssets().games.blank_portal;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
        initLabel();
    }

    private void initLabel() {
        label = LabelFactory.createLabel(text);
        label.setPosition(getX() + getWidth()/2 - label.getWidth()/2, getY() + 30);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        label.setPosition(getX() + getWidth()/2 - label.getWidth()/2, getY() + 30);
    }

}
