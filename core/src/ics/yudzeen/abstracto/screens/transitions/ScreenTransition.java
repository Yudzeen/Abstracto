package ics.yudzeen.abstracto.screens.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class for types of screen transition
 */

public abstract class ScreenTransition {

    public abstract float getDuration();

    public abstract void render(SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha);
}
