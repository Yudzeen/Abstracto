package ics.yudzeen.abstracto.screens.queue.simulator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;

/**
 * Queue container
 */

public class QueueContainer extends Actor {

    public String TAG = QueueContainer.class.getName();

    private static final int MAX_SIZE = 5;

    Abstracto game;
    QueueSimulatorScreen screen;
    NodeFactory nodeFactory;

    ArrayList<String> list;
    TextureAtlas.AtlasRegion texture;

    ArrayList<Node> nodesList;

    QueueContainer(Abstracto game, QueueSimulatorScreen screen, NodeFactory nodeFactory) {
        this.game = game;
        this.nodeFactory = nodeFactory;
        this.screen = screen;
        list = new ArrayList<>();
        nodesList = new ArrayList<>();
        texture = game.getAssets().simulator.queue_container;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
    }

    boolean enqueue(String s) {
        if(list.size() < MAX_SIZE) {
            list.add(list.size(), s);
            Node node = nodeFactory.createNode("-");
            node.setText(s);
            nodesList.add(node);
            game.androidInterfaces.toast("\""+ s + "\" enqueued.");
            return true;
        }
        else {
            game.androidInterfaces.toast("Queue Overflow!");
            return false;
        }
    }

    String dequeue() {
        Node node = nodesList.remove(0);
        game.androidInterfaces.toast("\""+ node.getText() + "\"  dequeued.");
        return list.remove(list.size()-1);
    }

    public int size() {
        return list.size();
    }

    public List<String> getElements() {
        return list;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
        for (int i = 0; i < nodesList.size(); i++) {
            final Node node = nodesList.get(i);
            if(i == 0) {
                node.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        dequeue();
                        node.remove();
                        final Node newNode = nodeFactory.createNode("-");
                        newNode.setText(node.getText().toString());
                        newNode.setPosition(getX()+getWidth()+10, getY()+getHeight()/2-newNode.getHeight()/2);
                        screen.addNodeListeners(newNode);
                        getStage().addActor(newNode);
                    }
                });
            }
            else {
                node.remove();
            }
            node.setPosition(getX()+10+node.getWidth()*(MAX_SIZE-i)-node.getWidth()/2, getY()+getHeight()/2-node.getHeight()/2);
            getStage().addActor(node);
        }
    }
}
