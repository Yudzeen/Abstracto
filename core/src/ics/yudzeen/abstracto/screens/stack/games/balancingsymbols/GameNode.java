package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Game node for balancing symbols game
 */

class GameNode extends Actor {

    static final String TAG = GameNode.class.getName();

    static final int WIDTH = 55;
    static final int HEIGHT = 55;

    GameRenderer gameRenderer;

    private TextureAtlas.AtlasRegion texture;

    private String text;
    private BitmapFont font;
    private GlyphLayout glyphLayout;

    private int stackPosition;

    private boolean currentNode;

    private boolean pushing;
    private boolean popping;
    private boolean merging;

    private boolean animating;

    private float blinkTimer;

    GameNode(GameRenderer gameRenderer, String text) {
        this.gameRenderer = gameRenderer;
        this.text = text;
        init();
    }

    private void init() {
        initTexture();
        initFont();
        initGlyphLayout();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(texture, getX(), getY());
        font.draw(batch, text, getX()+(getWidth()-getTextWidth())/2, getY()+getTextHeight()+(getHeight()-getTextHeight())/2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (isPushing()) {
            animatePush(delta);
        }

        if (isPopping()) {
            animatePop(delta);
        }

        if (isMerging()) {
            animateMerging(delta);
        }

    }

    private void animatePush(float delta) {
        final float pushingAnimationSpeed = 300;
        float x = getX();
        float y = getY();
        StackContainer stackContainer = gameRenderer.stackContainer;
        if (x > stackContainer.getX()+stackContainer.getWidth()/2-WIDTH/2) {
            setX(x-delta*pushingAnimationSpeed);
        }
        else {
            setX(stackContainer.getX()+stackContainer.getWidth()/2-WIDTH/2);
            if (y > stackContainer.getY()+10+stackPosition*HEIGHT) {
                setY(y-delta*pushingAnimationSpeed);
            }
            else {
                setY(stackContainer.getY()+10+stackPosition*HEIGHT);
                setPushing(false);
                gameRenderer.disablePushAndPopButtons(false);
            }
        }
    }

    private void animatePop(float delta) {
        final float poppingAnimationSpeed = 300;
        float x = getX();
        float y = getY();
        float poppedY = GameConstants.HEIGHT - getHeight() - 70;
        if (y < poppedY) {
            setY(y+delta*poppingAnimationSpeed);
        }
        else {
            setY(poppedY);
            setPopping(false);
            setMerging(true);
            gameRenderer.getCurrentNode().setMerging(true);
        }
    }

    private void animateMerging(float delta) {
        blink(delta);
        final float mergingAnimationSpeed = 250;
        float x = getX();
        float targetX = GameConstants.WIDTH/2 - WIDTH/2;
        if (!isCurrentNode()) {
            if (x < targetX) {
                setX(x+delta*mergingAnimationSpeed);
            }
            else {
                setX(targetX);
                setMerging(false);
                gameRenderer.removeMergingNodes();
                gameRenderer.disablePushAndPopButtons(false);
            }
        }
    }

    private void blink(float delta) {
        blinkTimer += delta;
        if (blinkTimer > 0.5) {
            blinkTimer = 0;
        }
        else if(blinkTimer > 0.25) {
            setTexture(gameRenderer.assets.games.light_yellow_circle);
        }
        else if (blinkTimer > 0){
            setTexture(gameRenderer.assets.simulator.blank_node);
        }
    }

    private void initTexture() {
        texture = gameRenderer.assets.simulator.blank_node;
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setBounds(0,0,WIDTH,HEIGHT);
    }

    private void initFont() {
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    private void initGlyphLayout() {
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        glyphLayout.setText(font, text);
    }

    private float getTextWidth() {
        return glyphLayout.width;
    }

    private float getTextHeight() {
        return glyphLayout.height;
    }

    public boolean isPushing() {
        return pushing;
    }

    public void setPushing(boolean pushing) {
        this.pushing = pushing;
    }

    public boolean isPopping() {
        return popping;
    }

    public void setPopping(boolean popping) {
        this.popping = popping;
    }

    public boolean isMerging() {
        return merging;
    }

    public void setMerging(boolean merging) {
        this.merging = merging;
    }

    public void setStackPosition(int stackPosition) {
        this.stackPosition = stackPosition;
    }

    public TextureAtlas.AtlasRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public boolean isCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(boolean currentNode) {
        this.currentNode = currentNode;
    }
}
