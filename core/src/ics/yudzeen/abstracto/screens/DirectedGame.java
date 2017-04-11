package ics.yudzeen.abstracto.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.transitions.ScreenTransition;

/**
 * Class for game transitions
 */

public abstract class DirectedGame implements ApplicationListener {

    private boolean init;
    private AbstractoScreen currScreen;
    private AbstractoScreen nextScreen;
    private FrameBuffer currFrameBuffer;
    private FrameBuffer nextFrameBuffer;
    private SpriteBatch batch;
    private float t;
    private ScreenTransition screenTransition;

    public void setScreen(AbstractoScreen screen) {
        setScreen(null, screen, null);
    }

    public void setScreen(AbstractoScreen screen, ScreenTransition transition) {
        setScreen(null, screen, transition);
    }

    public void setScreen(AbstractoScreen pScreen, AbstractoScreen nScreen, ScreenTransition screenTransition) {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        if(!init) {
            currFrameBuffer = new FrameBuffer(Pixmap.Format.RGB888, w, h, false);
            nextFrameBuffer = new FrameBuffer(Pixmap.Format.RGB888, w, h, false);
            batch = new SpriteBatch();
            init = true;
        }

        currScreen = pScreen;
        nextScreen = nScreen;
        nextScreen.show();
        nextScreen.resize(w, h);
        nextScreen.render(0);
        if(currScreen != null) {
            currScreen.pause();
        }
        nextScreen.pause();
        //Gdx.input.setInputProcessor(null);
        this.screenTransition = screenTransition;
        t = 0;
    }

    @Override
    public void render() {

        float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1.0f/60.0f);

        if(nextScreen == null) {
            if(currScreen != null) {
                currScreen.render(deltaTime);
            }
        }
        else {
            float duration = 0;
            if(screenTransition != null) {
                duration = screenTransition.getDuration();
            }
            t = Math.min(t+deltaTime, duration);
            if(screenTransition == null) {
                if(currScreen != null || t >= duration) {
                    if(currScreen != null) {
                        currScreen.hide();
                    }
                    nextScreen.resume();
                    Gdx.input.setInputProcessor(nextScreen.getInputProcessor());
                    currScreen = nextScreen;
                    nextScreen = null;
                    screenTransition = null;
                }
            }
            else {
                currFrameBuffer.begin();
                if(currScreen != null) {
                    currScreen.render(deltaTime);
                }
                currFrameBuffer.end();
                nextFrameBuffer.begin();
                nextScreen.render(deltaTime);
                nextFrameBuffer.end();

                float alpha = t/duration;
                screenTransition.render(batch, currFrameBuffer.getColorBufferTexture(), nextFrameBuffer.getColorBufferTexture(), alpha);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        if(currScreen != null) {
            currScreen.resize(width, height);
        }
        if(nextScreen != null) {
            nextScreen.resize(width, height);
        }
    }

    @Override
    public void pause() {
        if(currScreen != null) {
            currScreen.pause();
        }
    }

    @Override
    public void resume() {
        if(currScreen != null) {
            currScreen.resume();
        }
    }

    @Override
    public void dispose() {
        if(currScreen != null) {
            currScreen.hide();
        }
        if(nextScreen != null) {
            nextScreen.hide();
        }
        if(init) {
            currFrameBuffer.dispose();
            currScreen = null;
            nextFrameBuffer.dispose();
            nextScreen = null;
            batch.dispose();
            init = false;
        }
    }
}
