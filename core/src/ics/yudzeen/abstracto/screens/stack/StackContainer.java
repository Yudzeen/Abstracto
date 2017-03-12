package ics.yudzeen.abstracto.screens.stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.objects.Node;
import ics.yudzeen.abstracto.screens.objects.NodeFactory;

/**
 * Created by Yujin on 11/03/2017.
 */

public class StackContainer extends Actor {

    public String TAG = StackContainer.class.getName();

    public int MAX_SIZE = 5;

    Abstracto game;
    StackSimulatorScreen screen;
    NodeFactory nodeFactory;

    ArrayList<String> list;
    TextureAtlas.AtlasRegion texture;

    ArrayList<Node> nodesList;

    public StackContainer(Abstracto game, StackSimulatorScreen screen, NodeFactory nodeFactory) {
        this.game = game;
        this.nodeFactory = nodeFactory;
        this.screen = screen;
        list = new ArrayList<String>();
        nodesList = new ArrayList<Node>();
        texture = game.getAssets().simulator.stack_container;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
    }

    public boolean push(String s) {
        if(list.size() < MAX_SIZE) {
            list.add(list.size(), s);
            nodesList.add(nodeFactory.createNode(s));
            return true;
        }
        return false;
    }

    public String pop() {
        nodesList.remove(nodesList.size()-1);
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
            if(i == list.size()-1) {
                node.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        pop();
                        node.remove();
                        final Node newNode = nodeFactory.createNode(node.getText().toString());
                        newNode.setPosition(getX()+getWidth()/2-newNode.getWidth()/2, getY()+10+getHeight());
                        screen.addNodeListeners(newNode);
                        getStage().addActor(newNode);
                    }
                });
            }
            else {
                node.remove();
            }
            node.setPosition(getX()+getWidth()/2-node.getWidth()/2, getY()+10+node.getHeight()*i);
            getStage().addActor(node);
        }
    }
}
