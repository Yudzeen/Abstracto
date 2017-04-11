package ics.yudzeen.abstracto.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Yujin on 27/03/2017.
 */

public class LabelFactory {

    public static Color DEFAULT_COLOR = Color.BLACK;
    public static BitmapFont DEFAULT_FONT = new BitmapFont();

    public static Label createLabel(CharSequence text, BitmapFont bitmapFont, Color color) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = bitmapFont;
        style.fontColor = color;

        Label label = new Label(text, style);

        return label;
    }

    public static Label createLabel(CharSequence text) {
        return createLabel(text, DEFAULT_FONT, DEFAULT_COLOR);
    }

    public static Label createLabel(CharSequence text, BitmapFont bitmapFont) {
        return createLabel(text, bitmapFont, DEFAULT_COLOR);
    }
}
