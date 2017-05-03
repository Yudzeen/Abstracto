package ics.yudzeen.abstracto.screens.queue.games.customercashier;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;

/**
 * Queue actor
 */

class QueueContainer extends Actor {

    static final String TAG = QueueContainer.class.getName();

    static final int MAX_SIZE = 5;

    private Abstracto game;
    private TextureAtlas.AtlasRegion texture;
    int index;

    List<Customer> queue;

    public QueueContainer(Abstracto game, int index) {
        this.game = game;
        this.index = index;
        init();
    }

    private void init() {
        texture = game.getAssets().simulator.queue_container;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());

        queue = new ArrayList<>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    public void enqueue(Customer customer) throws QueueOverFlowException {
        if(queue.size() == MAX_SIZE) {
            throw new QueueOverFlowException("Queue is full.");
        }
        else {
            customer.disableDrag();
            queue.add(customer);
            customer.setPosition(getRight() - (customer.getWidth() + 18) * queue.size(),
                    getY() + getHeight()/2 - customer.getHeight()/2);
        }
    }

    public Customer dequeue() throws QueueUnderFlowException {
        if(queue.isEmpty()) {
            throw new QueueUnderFlowException("Queue is empty.");
        }
        else {
            Customer customer = queue.remove(0);
            updateQueue();
            return customer;
        }
    }

    public Customer peek() throws QueueUnderFlowException {
        if(queue.isEmpty()) {
            throw new QueueUnderFlowException("Queue is empty.");
        }
        else {
            return queue.get(0);
        }
    }

    public int size() {
        return queue.size();
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    private void updateQueue() {
        for (int i = 0; i < queue.size(); i++) {
            Customer customer = queue.get(i);
            customer.setPosition(getRight() - (customer.getWidth() + 18) * (i+1),
                    getY() + getHeight()/2 - customer.getHeight()/2);
        }
    }


    class QueueOverFlowException extends Exception {
        public QueueOverFlowException(String s) {
            super(s);
        }
    }

    class QueueUnderFlowException extends Exception {
        public QueueUnderFlowException(String s) {
            super(s);
        }
    }

}
