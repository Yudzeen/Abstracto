package ics.yudzeen.abstracto.screens.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ics.yudzeen.abstracto.Abstracto;

/**
 * A factory the creates image text button of nodes
 */

public class NodeFactory {

    Abstracto game;

    public NodeFactory(Abstracto game) {
        this.game = game;
    }

    public Node createNode(String text) {
        TextureRegion textureRegion = game.getAssets().simulator.blank_node;
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
        style.up = new TextureRegionDrawable(textureRegion);
        style.down = new TextureRegionDrawable(textureRegion).tint(Color.GRAY);
        style.over = new TextureRegionDrawable(textureRegion).tint(Color.LIGHT_GRAY);
        style.disabled = new TextureRegionDrawable(textureRegion).tint(Color.DARK_GRAY);
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;

        return new Node(text, style);
    }

}
