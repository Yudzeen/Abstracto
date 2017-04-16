package ics.yudzeen.abstracto.screens.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Cloud objects
 */

public class Cloud extends Actor {

    public static final String TAG = Cloud.class.getName();

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;

    private Abstracto game;
    private TextureAtlas.AtlasRegion texture;
    private int cloudType;
    private float animationSpeed;

    private Random random = new Random();

    public Cloud(Abstracto game, int cloudType) {
        this.game = game;
        this.cloudType = cloudType;
        init();
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
        setX(random.nextInt(850));
        setY(GameConstants.HEIGHT - getHeight() - 20);
        animationSpeed = random.nextInt(100) + 50;
    }

    private void init() {
        switch (cloudType) {
            case TYPE_1:
                texture = game.getAssets().images.cloud01;
                break;
            case TYPE_2:
                texture = game.getAssets().images.cloud02;
                break;
            case TYPE_3:
                texture = game.getAssets().images.cloud03;
                break;
            default:
                texture = game.getAssets().images.cloud01;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        final float x = getX();
        if(x + getWidth() > -10) {
            setX(x - delta * animationSpeed);
        }
        else {
            setX(GameConstants.WIDTH + 100);
            animationSpeed = random.nextInt(100) + 50;
        }
    }
}
