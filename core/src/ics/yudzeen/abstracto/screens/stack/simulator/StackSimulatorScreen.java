package ics.yudzeen.abstracto.screens.stack.simulator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.stack.StackMapScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
* Screen of the stack simulator
 */

public class StackSimulatorScreen extends AbstractoScreen {

    public static final String TAG = StackSimulatorScreen.class.getName();

    private NodeFactory nodeFactory;

    private Image backgroundImage;

    private Image titleBar;
    private Label titleLabel;

    private ImageButton addNodeButton;

    private Image trashcanImage;

    private StackContainer stackContainer;

    public StackSimulatorScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        nodeFactory = new NodeFactory(game.getAssets());

        initBackgroundImage();
        initTitleBar();
        initTitleLabel();
        initStackContainer();
        initAddNodeButton();
        initTrashCanImage();
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        stage.addActor(backgroundImage);
        stage.addActor(titleBar);
        stage.addActor(titleLabel);
        stage.addActor(addNodeButton);
        stage.addActor(trashcanImage);
        stage.addActor(stackContainer);
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        backgroundImage = new Image(new Texture(pixmap));
        pixmap.dispose();
    }

    private void initTitleBar() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, 45, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(74/255.0f,143/255.0f,231/255.0f,1));
        pixmap.fill();
        titleBar = new Image(new Texture(pixmap));
        titleBar.setPosition(0,GameConstants.HEIGHT-titleBar.getHeight());
        pixmap.dispose();
    }

    private void initTitleLabel() {
        titleLabel = LabelFactory.createLabel("Stack Simulator", assets.fonts.defaultBig, Color.WHITE);
        titleLabel.setPosition(10, GameConstants.HEIGHT - titleLabel.getHeight() - 5);
    }

    private void initStackContainer() {
        stackContainer = new StackContainer(game, this, nodeFactory);
        stackContainer.setPosition(GameConstants.WIDTH-stackContainer.getWidth()-50, 10);
    }

    private void initAddNodeButton() {
        addNodeButton = ButtonFactory.createImageButton(assets.simulator.add_node);
        addNodeButton.setPosition(10,GameConstants.HEIGHT-addNodeButton.getHeight()-60);
        addNodeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                spawnNode();
            }
        });
    }

    private void initTrashCanImage() {
        trashcanImage = new Image(assets.simulator.trash);
        trashcanImage.setPosition(10,10);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new StackMapScreen(game));
    }

    private void spawnNode() {
        final Node node = nodeFactory.createNode("-");
        node.setPosition(GameConstants.WIDTH/2 - node.getWidth()/2, GameConstants.HEIGHT/2 - node.getHeight()/2);
        addNodeListeners(node);
        stage.addActor(node);
    }

    public void addNodeListeners(final Node node) {
        node.addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                Gdx.app.debug(TAG, node.toString());
                node.moveBy(x - node.getWidth() / 2, y - node.getHeight() / 2);
            }

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {
                node.toFront();
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                Circle nodeCircle = new Circle(node.getX()+node.getWidth()/2, node.getY()+node.getHeight()/2, node.getHeight()/2);
                Rectangle stackRectangle = new Rectangle(stackContainer.getX(), stackContainer.getY(), stackContainer.getWidth(), stackContainer.getHeight());
                if(Intersector.overlaps(nodeCircle, stackRectangle)) {
                    if(stackContainer.push(node.getText().toString())){
                        node.remove();
                    }
                    else {
                        node.setPosition(GameConstants.WIDTH/2-node.getWidth()/2, GameConstants.HEIGHT/2-node.getHeight()/2);
                    }
                }
                Rectangle trashRectangle = new Rectangle(trashcanImage.getX(), trashcanImage.getY(), trashcanImage.getWidth(), trashcanImage.getHeight());
                if(Intersector.overlaps(nodeCircle, trashRectangle)) {
                    game.androidInterfaces.toast("\""+ node.getText() + "\" deleted.");
                    node.remove();
                }
            }
        });
        node.addListener(new ActorGestureListener(){
            @Override
            public boolean longPress(Actor actor, float x, float y) {
                renameNode(node);
                return true;
            }
        });
        node.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getTapCount() == 2) {
                    renameNode(node);
                }
            }
        });
    }

    public void renameNode(Node node) {
        Gdx.input.getTextInput(node.getTextInputAdapter(), "Enter node name", node.getText().toString().equals("-") ? "":node.getText().toString(), "Enter node name");
        Gdx.app.debug(TAG, "Node renaming, Entered: " + node.getTextInputAdapter().getText());
        if(node.getTextInputAdapter().getText() != null) {
            node.setText(node.getTextInputAdapter().getText());
        }
    }
}
