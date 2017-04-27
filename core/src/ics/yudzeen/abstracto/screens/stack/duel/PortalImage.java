package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import ics.yudzeen.abstracto.ui.LabelFactory;

/**
 * An image for the portal
 */

class PortalImage extends Actor {

    static final String TAG = PortalImage.class.getName();

    private DuelScreen gameScreen;
    private TextureAtlas.AtlasRegion texture;

    private Label label;
    private String text;

    private boolean drawNode;
    private Image node;
    private Label nodeLabel;

    PortalImage(DuelScreen gameScreen, String text) {
        this(gameScreen, text, gameScreen.getAssets().games.blank_portal);
    }

    PortalImage(DuelScreen gameScreen, String text, TextureAtlas.AtlasRegion texture) {
        this.gameScreen = gameScreen;
        this.text = text;
        this.texture = texture;
        init();
    }

    PortalImage(DuelScreen gameScreen) {
        this(gameScreen, "");
    }

    private void init() {
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

        if (drawNode) {
            node.draw(batch, parentAlpha);
            nodeLabel.draw(batch, parentAlpha);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        label.setPosition(getX() + getWidth()/2 - label.getWidth()/2, getY() + 30);
        if (node != null) {
            node.setPosition(getX() + getWidth()/2 - node.getWidth()/2, getY() + getHeight()*3/4 - 30);
            nodeLabel.setPosition(node.getX() + node.getWidth()/2 - nodeLabel.getWidth()/2,
                    node.getY() + node.getHeight()/2 - nodeLabel.getHeight()/2);
        }
    }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public void addNode(String text) {
        drawNode = true;
        node = new Image(gameScreen.getAssets().simulator.blank_node);
        node.setPosition(getX() + getWidth()/2 - node.getWidth()/2, getY() + getHeight()*3/4);
        nodeLabel = LabelFactory.createLabel(text);
        nodeLabel.setPosition(node.getX() + node.getWidth()/2 - nodeLabel.getWidth()/2,
                node.getY() + node.getHeight()/2 - nodeLabel.getHeight()/2);
    }

}
