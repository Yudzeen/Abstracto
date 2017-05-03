package ics.yudzeen.abstracto.screens.queue.games.customercashier;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Cashier actor
 */

class Cashier extends Actor {

    static final String TAG = Cashier.class.getName();

    private CustomerCashierScreen gameScreen;
    private TextureAtlas.AtlasRegion texture;
    private QueueContainer queueContainer;

    private boolean processing;
    private float blinkTimer;

    private Customer currentCustomer;

    Cashier(CustomerCashierScreen gameScreen, QueueContainer queueContainer) {
        this.gameScreen = gameScreen;
        this.queueContainer = queueContainer;
        init();
    }

    private void init() {
        texture = gameScreen.getAssets().games.cashier;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, getX(), getY());
    }

    public void act(float delta) {
        super.act(delta);

        if(processing) {
            blink(delta);
            if(currentCustomer.isOrderingFinished()) {
                processing = false;
                setTexture(gameScreen.getAssets().games.cashier);
            }
        }
        else {
            try {
                currentCustomer = queueContainer.peek();
                if(!currentCustomer.isOrderingFinished()) {
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
            setTexture(gameScreen.getAssets().games.cashier_light);
        }
        else if (blinkTimer > 0){
            setTexture(gameScreen.getAssets().games.cashier);
        }
    }

    private void process() {
        processing = true;
        currentCustomer.setOrdering(true);
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
