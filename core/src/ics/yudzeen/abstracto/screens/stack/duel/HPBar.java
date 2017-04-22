package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Health points bar actor
 */

class HPBar extends Actor {

    private Texture greenTexture;
    private Texture borderTexture;
    private List<Texture> grayTextures;

    private int totalHP;
    private int currentHP;
    private int borderWidth;

    private boolean flip;

    public HPBar(int width, int height, int totalHP, boolean flip) {
        this.totalHP = totalHP;
        this.flip = flip;
        setWidth((float) width);
        setHeight((float) height);
        init();
    }

    private void init() {
        borderWidth = 2;
        currentHP = totalHP;
        initBorderTexture();
        initGreenTexture();
        initGrayTextures();
    }

    private void initBorderTexture() {
        Pixmap pixmap = new Pixmap((int) getWidth(), (int) getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        borderTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    private void initGreenTexture() {
        Pixmap pixmap = new Pixmap((int) getWidth() - borderWidth*2, (int) getHeight() - borderWidth*2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                pixmap.drawPixel(i, j);
            }
        }
        greenTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    private void initGrayTextures() {
        grayTextures = new ArrayList<>();
        Pixmap grayPixmap = new Pixmap(greenTexture.getWidth() / totalHP, greenTexture.getHeight(), Pixmap.Format.RGBA8888);
        grayPixmap.setColor(Color.DARK_GRAY);
        grayPixmap.fill();

        for (int i = 0; i < totalHP; i++) {
            grayTextures.add(new Texture(grayPixmap));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(borderTexture, getX(), getY());
        batch.draw(greenTexture, getX() + borderWidth, getY() + borderWidth);

        for (int i=0; i < totalHP - currentHP; i++) {
            Texture grayTexture = grayTextures.get(i);
            if (!flip) {
                batch.draw(grayTexture, getX() + greenTexture.getWidth() - (i + 1) * grayTexture.getWidth() + borderWidth, getY() + borderWidth);
            } else {
                batch.draw(grayTexture, getX() + i * grayTexture.getWidth() + borderWidth, getY() + borderWidth);
            }
        }
    }

    public void setTotalHP(int totalHP) {
        this.totalHP = totalHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
}
