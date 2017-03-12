package ics.yudzeen.abstracto.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Yujin on 02/03/2017.
 */

public class Text extends Actor {

    BitmapFont font;
    String text;
    Color color;

    public Text(String text, BitmapFont font, Color color){
        this.text = text;
        this.font = font;
        this.color = color;
        GlyphLayout layout = new GlyphLayout(font, text);
        this.setWidth(layout.width);
        this.setHeight(layout.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.setColor(color);
        font.draw(batch, text, getX(), getY());
    }


}
