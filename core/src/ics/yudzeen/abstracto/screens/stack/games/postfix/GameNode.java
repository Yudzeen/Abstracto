package ics.yudzeen.abstracto.screens.stack.games.postfix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Node for postfix expression game
 */

class GameNode extends Actor {

    public static final String TAG = GameNode.class.getName();

    public static final int WIDTH = 55;
    public static final int HEIGHT = 55;

    private PostfixExpressionGameScreen gameScreen;

    private TextureAtlas.AtlasRegion texture;

    private String text;
    private BitmapFont font;
    private GlyphLayout glyphLayout;

    private boolean pushing;
    private boolean popping;
    private boolean merging;

    private StackContainer stackContainer;
    private GameRenderer gameRenderer;
    private int stackPosition;

    private PoppedNodes poppedNodes;
    private int poppedPosition;

    private float switchColorTimer;

    public GameNode(PostfixExpressionGameScreen gameScreen, GameRenderer gameRenderer, String text) {
        this.gameScreen = gameScreen;
        this.gameRenderer = gameRenderer;
        this.text = text;
        init();
    }

    private void init() {
        initTexture();
        initFont();
        initGlyphLayout();
    }

    private void initTexture() {
        texture = gameScreen.getAssets().simulator.blank_node;
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());

        font.draw(batch, text, getX()+(getWidth()-getTextWidth())/2, getY()+getTextHeight()+(getHeight()-getTextHeight())/2);
    }

    @Override
    public void act(float delta) {
        if(isPushing()) {
            final float pushingAnimationSpeed = 300;
            float x = getX();
            float y = getY();
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
        if(isPopping()) {
            setPushing(false);
            final float poppingAnimationSpeed = 300;
            float x = getX();
            float y = getY();
            float poppedY = poppedNodes.getTempNodes()[0].getY();
            if (y < poppedY) {
                setY(y+delta*poppingAnimationSpeed);
            }
            else {
                setY(poppedY);
                switch (poppedPosition) {
                    case 0:
                        float firstPopX = poppedNodes.getTempNodes()[0].getX();
                        if(x < firstPopX) {
                            setX(x+delta*poppingAnimationSpeed);
                        }
                        else {
                            setX(firstPopX);
                            setPopping(false);
                            poppedNodes.merge();
                        }
                        break;
                    case 1:
                        float secondPopX = poppedNodes.getTempNodes()[1].getX();
                        if(x < secondPopX) {
                            setX(x+delta*poppingAnimationSpeed);
                        }
                        else {
                            setX(secondPopX);
                            setPopping(false);
                            gameRenderer.disablePushAndPopButtons(false);
                        }
                        break;
                    default:
                        Gdx.app.debug(TAG, "Unknown popped position.");
                }
            }
        }
        if(isMerging()) {
            if(isPopping()) {
                setPosition(poppedNodes.getTempNodes()[poppedPosition].getX(), poppedNodes.getTempNodes()[poppedPosition].getY());
                setPopping(false);
            }
            final float mergingAnimationSpeed = 250;
            final float targetX = 397.5f;
            final float targetY = 355f;
            float x = getX();

            switchColorTimer += delta;
            if (switchColorTimer > 0.5) {
                switchColorTimer = 0;
            }
            else if(switchColorTimer > 0.25) {
                setTexture(gameScreen.getAssets().games.light_yellow_circle);
            }
            else if (switchColorTimer > 0){
                setTexture(gameScreen.getAssets().simulator.blank_node);
            }

            setY(targetY);
            switch (poppedPosition) {
                case 0:
                    if(x < targetX) {
                        setX(x+delta*mergingAnimationSpeed);
                    }
                    else {
                        setX(targetX);
                        setMerging(false);
                        if(!gameRenderer.gameOver) {
                            gameRenderer.disablePushAndPopButtons(false);
                        }
                    }
                    break;
                case 1:
                    if(x > targetX) {
                        setX(x-delta*mergingAnimationSpeed);
                    }
                    else {
                        setX(targetX);
                        setMerging(false);
                        if(!gameRenderer.gameOver) {
                            gameRenderer.disablePushAndPopButtons(false);
                        }
                        gameRenderer.removeEquationNodes();
                    }
                    break;

            }
        }
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

    public StackContainer getStackContainer() {
        return stackContainer;
    }

    public void setStackContainer(StackContainer stackContainer) {
        this.stackContainer = stackContainer;
    }

    public int getStackPosition() {
        return stackPosition;
    }

    public void setStackPosition(int stackPosition) {
        this.stackPosition = stackPosition;
    }

    public void setPoppedPosition(int poppedPosition) {
        this.poppedPosition = poppedPosition;
    }

    public int getPoppedPosition() {
        return poppedPosition;
    }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

    public void setPoppedNodes(PoppedNodes poppedNodes) {
        this.poppedNodes = poppedNodes;
    }

    public boolean isMerging() {
        return merging;
    }

    public void setMerging(boolean merging) {
        this.merging = merging;
    }
}
