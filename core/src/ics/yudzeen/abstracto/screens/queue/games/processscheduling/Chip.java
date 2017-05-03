package ics.yudzeen.abstracto.screens.queue.games.processscheduling;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Chip
 */

class Chip extends Actor {

    static final String TAG = Chip.class.getName();

    private ProcessSchedulingGameScreen gameScreen;
    private TextureAtlas.AtlasRegion texture;
    private QueueContainer queueContainer;

    private boolean processing;
    private float blinkTimer;

    private Process currentProcess;

    Chip(ProcessSchedulingGameScreen gameScreen, QueueContainer queueContainer) {
        this.gameScreen = gameScreen;
        this.queueContainer = queueContainer;
        init();
    }

    private void init() {
        texture = gameScreen.getAssets().games.chip;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(processing) {
            blink(delta);
            if(currentProcess.isProcessingFinished()) {
                processing = false;
                setTexture(gameScreen.getAssets().games.chip);
            }
        }
        else {
            try {
                currentProcess = queueContainer.peek();
                if(!currentProcess.isProcessingFinished()) {
                    process();
                }
            } catch (QueueContainer.QueueUnderFlowException e) { }
        }
    }

    private void blink(float delta) {
        blinkTimer += delta;
        if (blinkTimer > 0.5) {
            blinkTimer = 0;
        }
        else if(blinkTimer > 0.25) {
            setTexture(gameScreen.getAssets().games.chip_light);
        }
        else if (blinkTimer > 0){
            setTexture(gameScreen.getAssets().games.chip);
        }
    }

    private void process() {
        processing = true;
        currentProcess.setProcessing(true);
    }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }
}
