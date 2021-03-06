package ics.yudzeen.abstracto.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Factory for creating text and image buttons
 */

public class ButtonFactory {

    // Button defaults
    private static final int DEFAULT_WIDTH = 120;
    private static final int DEFAULT_HEIGHT = 50;
    private static final Color DEFAULT_COLOR = new Color(89/255f,210/255f,254/255f,1);

    private ButtonFactory() {}

    public static TextButton createTextButton(String text, int width, int height, Color bgColor, Color fontColor, BitmapFont font) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(bgColor);
        pixmap.fill();

        TextureRegion background = new TextureRegion(new Texture(pixmap));
        pixmap.dispose();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(background);
        textButtonStyle.down = new TextureRegionDrawable(background).tint(Color.GRAY);
        textButtonStyle.over = new TextureRegionDrawable(background).tint(Color.LIGHT_GRAY);
        textButtonStyle.disabled = new TextureRegionDrawable(background).tint(Color.DARK_GRAY);
        textButtonStyle.font = font;
        textButtonStyle.fontColor = fontColor;

        return new TextButton(text, textButtonStyle);
    }

    public static TextButton createTextButton(String text, int width, int height, Color color) {
        return createTextButton(text, width, height, color, Color.BLACK, new BitmapFont());
    }

    public static TextButton createTextButton(String text) {
        return createTextButton(text, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR);
    }

    public static TextButton createTextButton(String text, int width, int height) {
        return createTextButton(text, width, height, DEFAULT_COLOR);
    }

    public static TextButton createTextButton(String text, int width, int height, Color fontColor, BitmapFont bitmapFont) {
        return createTextButton(text, width, height, DEFAULT_COLOR, fontColor, bitmapFont);
    }

    public static ImageButton createImageButton(TextureRegion region, boolean flipX, boolean flipY) {
        region.flip(flipX, flipY);
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = new TextureRegionDrawable(region);
        imageButtonStyle.down = new TextureRegionDrawable(region).tint(Color.GRAY);
        imageButtonStyle.over = new TextureRegionDrawable(region).tint(Color.LIGHT_GRAY);
        imageButtonStyle.disabled = new TextureRegionDrawable(region).tint(Color.DARK_GRAY);

        return new ImageButton(imageButtonStyle);
    }

    public static ImageButton createImageButton(TextureRegion region) {
        return createImageButton(region, false, false);
    }

}
