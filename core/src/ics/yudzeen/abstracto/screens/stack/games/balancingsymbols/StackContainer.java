package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Stack;

/**
 * Stack Container for balancing symbols game
 */

class StackContainer extends Actor {

    public static final String TAG = StackContainer.class.getName();

    private GameRenderer gameRenderer;

    private TextureAtlas.AtlasRegion texture;
    private Stack<GameNode> stack;

    public StackContainer(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
        init();
    }

    private void init() {
        texture = gameRenderer.assets.simulator.stack_container;
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());

        stack = new Stack<>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    public void push(GameNode node) {
        stack.push(node);
        node.setPushing(true);
        node.setStackPosition(stack.size()-1);
    }

    public GameNode pop() {
        GameNode node = stack.pop();
        node.setStackPosition(-1);
        node.setPopping(true);
        return node;

    }

}
