package ics.yudzeen.abstracto.screens.stack.school.simulator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.Abstracto;

/**
 * Stack container of the simulator
 */

class StackContainer extends Actor {

    public String TAG = StackContainer.class.getName();

    private static final int MAX_SIZE = 5;

    Abstracto game;
    StackSimulatorScreen screen;
    ics.yudzeen.abstracto.screens.stack.school.simulator.NodeFactory nodeFactory;

    ArrayList<String> list;
    TextureAtlas.AtlasRegion texture;

    ArrayList<ics.yudzeen.abstracto.screens.stack.school.simulator.Node> nodesList;

    StackContainer(Abstracto game, StackSimulatorScreen screen, ics.yudzeen.abstracto.screens.stack.school.simulator.NodeFactory nodeFactory) {
        this.game = game;
        this.nodeFactory = nodeFactory;
        this.screen = screen;
        list = new ArrayList<String>();
        nodesList = new ArrayList<ics.yudzeen.abstracto.screens.stack.school.simulator.Node>();
        texture = game.getAssets().simulator.stack_container;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
    }

    boolean push(String s) {
        if(list.size() < MAX_SIZE) {
            list.add(list.size(), s);
            ics.yudzeen.abstracto.screens.stack.school.simulator.Node node = nodeFactory.createNode("-");
            node.setText(s);
            nodesList.add(node);
            game.androidInterfaces.toast("\""+ s + "\" pushed.");
            return true;
        }
        else {
            game.androidInterfaces.toast("Stack Overflow!");
            return false;
        }
    }

    String pop() {
        ics.yudzeen.abstracto.screens.stack.school.simulator.Node node = nodesList.remove(nodesList.size()-1);
        game.androidInterfaces.toast("\""+ node.getText() + "\"  popped.");
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
            final ics.yudzeen.abstracto.screens.stack.school.simulator.Node node = nodesList.get(i);
            if(i == list.size()-1) {
                node.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        pop();
                        node.remove();
                        final ics.yudzeen.abstracto.screens.stack.school.simulator.Node newNode = nodeFactory.createNode("-");
                        newNode.setText(node.getText().toString());
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
