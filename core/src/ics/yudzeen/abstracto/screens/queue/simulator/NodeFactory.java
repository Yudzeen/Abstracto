package ics.yudzeen.abstracto.screens.queue.simulator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ics.yudzeen.abstracto.utils.Assets;

/**
 * A factory the creates image text button of nodes
 */

public class NodeFactory {

    private static final boolean DEFAULT_DISABLED = false;

    Assets assets;

    NodeFactory(Assets assets) {
        this.assets = assets;
    }

    /**
     * Creates a node
     * @param text
     * @param isDisabled button
     * @return Node
     */
    Node createNode(String text, boolean isDisabled) {
        TextureRegion textureRegion = assets.simulator.blank_node;
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
        style.up = new TextureRegionDrawable(textureRegion);
        style.down = new TextureRegionDrawable(textureRegion).tint(Color.GRAY);
        style.over = new TextureRegionDrawable(textureRegion).tint(Color.LIGHT_GRAY);
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        Node node = new Node(text, style);
        node.setDisabled(isDisabled);
        return node;
    }

    /**
     * Creates a node
     * @param text
     * @return Node
     */
    Node createNode(String text) {
        return createNode(text, DEFAULT_DISABLED);
    }

}
