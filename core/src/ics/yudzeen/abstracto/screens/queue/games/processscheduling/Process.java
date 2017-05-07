package ics.yudzeen.abstracto.screens.queue.games.processscheduling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * Process
 */

class Process extends Actor {

    static final String TAG = Process.class.getName();

    static final int TYPE_1 = 1;
    static final int TYPE_2 = 2;
    static final int TYPE_3 = 3;

    static final int TYPE_1_PROCESSING_TIME = 3;
    static final int TYPE_2_PROCESSING_TIME = 5;
    static final int TYPE_3_PROCESSING_TIME = 10;


    private Process that;
    private int type;
    private float processingTimer;
    private float processingTime;

    private ProcessSchedulingGameScreen gameScreen;
    private GameRenderer gameRenderer;
    private TextureAtlas.AtlasRegion texture;
    private DragListener dragListener;

    private float originalX;
    private float originalY;

    private boolean processing;
    private boolean processingFinished;
    private float blinkTimer;

    private QueueContainer queueContainer;

    public Process(ProcessSchedulingGameScreen gameScreen, GameRenderer gameRenderer, int type) {
        this.gameScreen = gameScreen;
        this.gameRenderer = gameRenderer;
        this.type = type;
        that = this;
        init();
    }

    private void init() {
        switch (type) {
            case TYPE_1:
                texture = gameScreen.getAssets().games.process_blue_light;
                processingTime = TYPE_1_PROCESSING_TIME;
                break;
            case TYPE_2:
                texture = gameScreen.getAssets().games.process_yellow_light;
                processingTime = TYPE_2_PROCESSING_TIME;
                break;
            case TYPE_3:
                texture = gameScreen.getAssets().games.process_red_light;
                processingTime = TYPE_3_PROCESSING_TIME;
                break;
            default:
                Gdx.app.debug(TAG, "Unknown type.");
        }

        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());

        initDragListener();
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
                            gameRenderer.processArea.getProcessList().remove(that);
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
                    texture = gameScreen.getAssets().games.process_blue;
                    break;
                case TYPE_2:
                    texture = gameScreen.getAssets().games.process_yellow;
                    break;
                case TYPE_3:
                    texture = gameScreen.getAssets().games.process_red;
                    break;
                default:
                    Gdx.app.debug(TAG, "Unknown type.");
            }
        }
        else if (blinkTimer > 0){
            switch (type) {
                case TYPE_1:
                    texture = gameScreen.getAssets().games.process_blue_light;
                    break;
                case TYPE_2:
                    texture = gameScreen.getAssets().games.process_yellow_light;
                    break;
                case TYPE_3:
                    texture = gameScreen.getAssets().games.process_red_light;
                    break;
                default:
                    Gdx.app.debug(TAG, "Unknown type.");
            }
        }
    }

    private void process(float delta) {
        processingTimer += delta;
        if(processingTimer > processingTime) {
            processing = false;
            processingFinished = true;
            switch (type) {
                case TYPE_1:
                    texture = gameScreen.getAssets().games.process_dark;
                    break;
                case TYPE_2:
                    texture = gameScreen.getAssets().games.process_dark;
                    break;
                case TYPE_3:
                    texture = gameScreen.getAssets().games.process_dark;
                    break;
                default:
                    Gdx.app.debug(TAG, "Unknown type.");
            }
            addDequeueListener();
        }
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

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void disableDrag() {
        removeListener(dragListener);
    }

    public void setOriginalX(float originalX) {
        this.originalX = originalX;
    }

    public void setOriginalY(float originalY) {
        this.originalY = originalY;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isProcessingFinished() {
        return processingFinished;
    }
}
