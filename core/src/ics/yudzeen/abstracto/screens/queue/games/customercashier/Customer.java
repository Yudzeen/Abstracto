package ics.yudzeen.abstracto.screens.queue.games.customercashier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * Customer actor
 */

class Customer extends Actor {

    static final String TAG = Customer.class.getName();

    static final int TYPE_1 = 1;
    static final int TYPE_2 = 2;
    static final int TYPE_3 = 3;

    static final int TYPE_1_ORDERING_TIME = 3;
    static final int TYPE_2_ORDERING_TIME = 5;
    static final int TYPE_3_ORDERING_TIME = 10;

    private Customer that;
    private int type;
    private float orderingTimer;
    private float orderingTime;

    private CustomerCashierScreen gameScreen;
    private GameRenderer gameRenderer;
    private TextureAtlas.AtlasRegion texture;
    private DragListener dragListener;

    private float originalX;
    private float originalY;

    private boolean ordering;
    private boolean orderingFinished;
    private float blinkTimer;

    private QueueContainer queueContainer;

    Customer(CustomerCashierScreen gameScreen, GameRenderer gameRenderer, int type) {
        this.gameScreen = gameScreen;
        this.gameRenderer = gameRenderer;
        this.type = type;
        that = this;
        init();
    }

    private void init() {
        switch (type) {
            case TYPE_1:
                texture = gameScreen.getAssets().games.man_light;
                orderingTime = TYPE_1_ORDERING_TIME;
                break;
            case TYPE_2:
                texture = gameScreen.getAssets().games.woman_light;
                orderingTime = TYPE_2_ORDERING_TIME;
                break;
            case TYPE_3:
                texture = gameScreen.getAssets().games.kid_light;
                orderingTime = TYPE_3_ORDERING_TIME;
                break;
            default:
                Gdx.app.debug(TAG, "Unknown type.");
        }

        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());

        initDragListener();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(ordering) {
            blink(delta);
            process(delta);
        }
    }

    private void blink(float delta) {
        blinkTimer += delta;
        if (blinkTimer > 0.5) {
            blinkTimer = 0;
        }
        else if(blinkTimer > 0.25) {
            switch (type) {
                case TYPE_1:
                    texture = gameScreen.getAssets().games.man;
                    break;
                case TYPE_2:
                    texture = gameScreen.getAssets().games.woman;
                    break;
                case TYPE_3:
                    texture = gameScreen.getAssets().games.kid;
                    break;
                default:
                    Gdx.app.debug(TAG, "Unknown type.");
            }
        }
        else if (blinkTimer > 0){
            switch (type) {
                case TYPE_1:
                    texture = gameScreen.getAssets().games.man_light;
                    break;
                case TYPE_2:
                    texture = gameScreen.getAssets().games.woman_light;
                    break;
                case TYPE_3:
                    texture = gameScreen.getAssets().games.kid_light;
                    break;
                default:
                    Gdx.app.debug(TAG, "Unknown type.");
            }
        }
    }

    private void process(float delta) {
        orderingTimer += delta;
        if(orderingTimer > orderingTime) {
            ordering = false;
            orderingFinished = true;
            switch (type) {
                case TYPE_1:
                    texture = gameScreen.getAssets().games.man_dark;
                    break;
                case TYPE_2:
                    texture = gameScreen.getAssets().games.woman_dark;
                    break;
                case TYPE_3:
                    texture = gameScreen.getAssets().games.kid_dark;
                    break;
                default:
                    Gdx.app.debug(TAG, "Unknown type.");
            }
            addDequeueListener();
        }
    }

    private void initDragListener() {
        dragListener = new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                that.moveBy(x - that.getWidth() / 2, y - that.getHeight() / 2);
            }

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                that.toFront();
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                boolean flag = false;
                for (int i = 0; i < gameRenderer.queueContainerList.size(); i++) {
                    QueueContainer temp = gameRenderer.queueContainerList.get(i);
                    Rectangle queueContainerRect = temp.getRectangle();
                    Rectangle selfRect = getRectangle();
                    if(!flag && Intersector.overlaps(queueContainerRect, selfRect)) {
                        try {
                            temp.enqueue(that);
                            flag = true;
                            queueContainer = temp;
                            gameRenderer.spawnArea.getCustomerList().remove(that);
                            Gdx.app.debug(TAG, "Enqueued. Queue Container #" + i + " " + queueContainer.queue);
                        } catch (QueueContainer.QueueOverFlowException e) {
                            Gdx.app.debug(TAG, "Queue overflow.");
                            that.setPosition(originalX, originalY);
                        }
                    }
                }
                if(!flag) {
                    that.setPosition(originalX, originalY);
                }
            }
        };
        addListener(dragListener);
    }

    private void addDequeueListener() {
        ActorGestureListener actorGestureListener = new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                that.remove();
                try {
                    queueContainer.dequeue();
                    Gdx.app.debug(TAG, "Dequeued. Queue Container #" + queueContainer.index + " " + queueContainer.queue);
                } catch (QueueContainer.QueueUnderFlowException e) {
                    e.printStackTrace();
                }
            }
        };
        addListener(actorGestureListener);
    }

    public void disableDrag() {
        removeListener(dragListener);
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isOrderingFinished() {
        return orderingFinished;
    }

    public void setOrderingFinished(boolean orderingFinished) {
        this.orderingFinished = orderingFinished;
    }

    public boolean isOrdering() {
        return ordering;
    }

    public void setOrdering(boolean ordering) {
        this.ordering = ordering;
    }

    public void setOriginalX(float originalX) {
        this.originalX = originalX;
    }

    public void setOriginalY(float originalY) {
        this.originalY = originalY;
    }
}
