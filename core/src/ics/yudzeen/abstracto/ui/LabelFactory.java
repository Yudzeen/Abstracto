package ics.yudzeen.abstracto.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Factory for creating labels
 */

public class LabelFactory {

    private static Color DEFAULT_COLOR = Color.BLACK;
    private static BitmapFont DEFAULT_FONT = new BitmapFont();

    private LabelFactory() {}

    public static Label createLabel(CharSequence text, BitmapFont bitmapFont, Color color) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = bitmapFont;
        style.fontColor = color;

        return new Label(text, style);
    }

    public static Label createLabel(CharSequence text) {
        return createLabel(text, DEFAULT_FONT, DEFAULT_COLOR);
    }

    public static Label createLabel(CharSequence text, Color color) {
        return createLabel(text, DEFAULT_FONT, color);
    }

    public static Label createLabel(CharSequence text, BitmapFont bitmapFont) {
        return createLabel(text, bitmapFont, DEFAULT_COLOR);
    }
}
