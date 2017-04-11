package ics.yudzeen.abstracto.screens.stack.postfix;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import ics.yudzeen.abstracto.screens.stack.simulator.Node;
import ics.yudzeen.abstracto.screens.stack.simulator.NodeFactory;

/**
 * The stack container for the postfix expression game
 */

public class StackContainer extends Actor {

    public static final String TAG = StackContainer.class.getName();

    private PostfixExpressionGameScreen gameScreen;

    private TextureAtlas.AtlasRegion texture;

    private List<GameNode> nodeStack;

    public StackContainer(PostfixExpressionGameScreen gameScreen) {
        this.gameScreen = gameScreen;
        init();
    }

    private void init() {
        texture = gameScreen.getAssets().simulator.stack_container;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());

        nodeStack = new ArrayList<GameNode>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    public void push(GameNode node) {
        nodeStack.add(node);
        node.setStackContainer(this);
        node.setStackPosition(size()-1);
    }

    public GameNode pop() {
        return nodeStack.remove(nodeStack.size()-1);
    }

    public int size() {
        return nodeStack.size();
    }
}
