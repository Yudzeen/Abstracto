package ics.yudzeen.abstracto.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Text Field Factory
 */

public class TextFieldFactory {

    private static final Color defaultColor = Color.BLACK;
    private static final int DEFAULT_WIDTH = 250;
    private static final int DEFAULT_HEIGHT = 50;

    public static TextField createTextField(String text, BitmapFont font, Color fontColor) {
        Pixmap pixmap = new Pixmap(DEFAULT_WIDTH, DEFAULT_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        TextureRegion background = new TextureRegion(new Texture(pixmap));

        Skin skin = new Skin(Gdx.files.internal(GameConstants.SKIN_LIBGDX_UI), new TextureAtlas(GameConstants.TEXTURE_ATLAS_LIBGDX_UI));

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = fontColor;
        textFieldStyle.background = new TextureRegionDrawable(background);
        textFieldStyle.cursor = skin.newDrawable("cursor", Color.BLACK);

        TextField textField = new TextField(text, textFieldStyle);
        textField.setWidth(DEFAULT_WIDTH);
        skin.dispose();
        return textField;
    }

}
