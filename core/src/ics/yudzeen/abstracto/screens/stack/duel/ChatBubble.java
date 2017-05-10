package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Chat bubble
 */

class ChatBubble extends Actor {

    static final String TAG = ChatBubble.class.getName();

    private DuelScreen duelScreen;
    private TextureRegion textureRegion;

    private float showDuration;

    public ChatBubble(DuelScreen duelScreen) {
        this.duelScreen = duelScreen;
        init();
    }

    private void init() {
        textureRegion = new TextureRegion(duelScreen.getAssets().games.too_slow);
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(showDuration > 0) {
            setVisible(true);
        }
        else {
            setVisible(false);
        }
        showDuration -= delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, getX(), getY());
    }

    public void show(float showDuration) {
        this.showDuration = showDuration;
    }
}
