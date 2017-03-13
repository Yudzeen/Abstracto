package ics.yudzeen.abstracto.screens.stack;

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
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.objects.Node;
import ics.yudzeen.abstracto.screens.objects.NodeFactory;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.utils.GameConstants;
import ics.yudzeen.abstracto.utils.Text;

/**
* Screen of the stack simulator
 */

// // TODO: 12/03/2017 Instructions/Controls

public class StackSimulatorScreen extends AbstractoScreen {

    public static final String TAG = StackSimulatorScreen.class.getName();

    NodeFactory nodeFactory;
    StackContainer stackContainer;
    Image trashCan;

    public StackSimulatorScreen(Abstracto game) {
        super(game);
        nodeFactory = new NodeFactory(game);
    }

    @Override
    protected void buildStage() {
        super.buildStage();
        buildBackground();
        buildTitleBar();
        buildTitle();
        buildAddNodeButton();
        buildStackContainer();
        buildTrashCan();
    }

    private void buildBackground() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.draw(pixmap,0,0);
        Image background = new Image(texture);
        stage.addActor(background);
    }

    private void buildTitleBar() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, 45, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(74/255.0f,143/255.0f,231/255.0f,1));
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        texture.draw(pixmap,0,0);
        Image background = new Image(texture);
        background.setPosition(0,GameConstants.HEIGHT-background.getHeight());
        stage.addActor(background);
    }

    private void buildTitle() {
        Text title = new Text("Stack Simulator", assets.fonts.defaultBig, Color.WHITE);
        title.setPosition(10, GameConstants.HEIGHT - 10);
        stage.addActor(title);
    }

    private void buildStackContainer() {
        stackContainer = new StackContainer(game, this, nodeFactory);
        stackContainer.setPosition(GameConstants.WIDTH-stackContainer.getWidth()-50, 10);
        stage.addActor(stackContainer);
    }

    private void buildAddNodeButton() {
        ImageButton addNodeButton = ButtonFactory.createImageButton(assets.simulator.add_node);
        addNodeButton.setPosition(10,GameConstants.HEIGHT-addNodeButton.getHeight()-60);
        addNodeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                spawnNode();
            }
        });
        stage.addActor(addNodeButton);
    }

    private void buildTrashCan() {
        trashCan = new Image(assets.simulator.trash);
        trashCan.setPosition(10,10);
        stage.addActor(trashCan);
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
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                Circle nodeCircle = new Circle(node.getX()+node.getWidth()/2, node.getY()+node.getHeight()/2, node.getHeight()/2);
                Rectangle stackRectangle = new Rectangle(stackContainer.getX(), stackContainer.getY(), stackContainer.getWidth(), stackContainer.getHeight());
                if(Intersector.overlaps(nodeCircle, stackRectangle)) {
                    if(stackContainer.push(node.getText().toString())){
                        node.remove();
                    }
                }
                Rectangle trashRectangle = new Rectangle(trashCan.getX(), trashCan.getY(), trashCan.getWidth(), trashCan.getHeight());
                if(Intersector.overlaps(nodeCircle, trashRectangle)) {
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
