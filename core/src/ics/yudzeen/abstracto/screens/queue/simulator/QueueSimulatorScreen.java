package ics.yudzeen.abstracto.screens.queue.simulator;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import ics.yudzeen.abstracto.Abstracto;
import ics.yudzeen.abstracto.screens.AbstractoScreen;
import ics.yudzeen.abstracto.screens.queue.QueueMapScreen;
import ics.yudzeen.abstracto.screens.queue.school.SchoolScreen;
import ics.yudzeen.abstracto.ui.ButtonFactory;
import ics.yudzeen.abstracto.ui.LabelFactory;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Queue simulator screen
 */

public class QueueSimulatorScreen extends AbstractoScreen {

    public static final String TAG = QueueSimulatorScreen.class.getName();

    private Image backgroundImage;
    private Image titleBar;
    private Label titleLabel;
    private Label headLabel;
    private Label tailLabel;

    private ImageButton addNodeButton;
    private Image trashcanImage;

    private NodeFactory nodeFactory;
    private TextButton helpButton;
    private Image helpPanelImage;
    private Label helpLabel;
    private boolean helpVisible = false;

    private QueueContainer queueContainer;

    private int nodeName;

    public QueueSimulatorScreen(Abstracto game) {
        super(game);
        init();
    }

    private void init() {
        nodeFactory = new NodeFactory(getAssets());
        nodeName = 1;

        initBackgroundImage();
        initTitleBar();
        initTitleLabel();
        initAddNodeButton();
        initTrashCanImage();
        initQueueContainer();

        initHeadLabel();
        initTailLabel();
        initHelpButton();
        initHelpPanelImage();
        initHelpLabel();
    }

    @Override
    protected void buildStage() {
        super.buildStage();

        stage.addActor(backgroundImage);
        stage.addActor(titleBar);
        stage.addActor(titleLabel);
        stage.addActor(addNodeButton);
        stage.addActor(trashcanImage);
        stage.addActor(queueContainer);

        stage.addActor(headLabel);
        stage.addActor(tailLabel);

        stage.addActor(helpButton);
        stage.addActor(helpPanelImage);
        stage.addActor(helpLabel);
    }

    @Override
    protected void backKeyPressed() {
        game.setScreen(new SchoolScreen(game));
    }

    private void initBackgroundImage() {
        Pixmap pixmap = new Pixmap(GameConstants.WIDTH, GameConstants.HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(new Color(109/255.0f, 196/255.0f, 255/255.0f, 1.0f));
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
        titleLabel = LabelFactory.createLabel("Queue Simulator", assets.fonts.verdana_30, Color.WHITE);
        titleLabel.setPosition(10, GameConstants.HEIGHT - titleLabel.getHeight() - 5);
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

    private void initQueueContainer() {
        queueContainer = new QueueContainer(game, this, nodeFactory);
        queueContainer.setPosition(GameConstants.WIDTH - queueContainer.getWidth() - 100,
                GameConstants.HEIGHT - titleBar.getHeight() - queueContainer.getHeight() - 20);
    }

    private void initHeadLabel() {
        headLabel = LabelFactory.createLabel("HEAD", assets.fonts.verdana_20, Color.BLACK);
        headLabel.setPosition(queueContainer.getRight() - headLabel.getWidth()/2,
                queueContainer.getY() - 25);
    }

    private void initTailLabel() {
        tailLabel = LabelFactory.createLabel("TAIL", assets.fonts.verdana_20, Color.BLACK);
        tailLabel.setPosition(queueContainer.getX() - tailLabel.getWidth()/2,
                queueContainer.getY() - 25);
    }

    private void spawnNode() {
        final Node node = nodeFactory.createNode(Integer.toString(nodeName));
        node.setPosition(GameConstants.WIDTH/2 - node.getWidth()/2, GameConstants.HEIGHT/2 - node.getHeight()/2);
        addNodeListeners(node);
        stage.addActor(node);
        nodeName++;
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
                Rectangle queueRectangle = new Rectangle(queueContainer.getX(), queueContainer.getY(), queueContainer.getWidth(), queueContainer.getHeight());
                if(Intersector.overlaps(nodeCircle, queueRectangle)) {
                    if(queueContainer.enqueue(node.getText().toString())){
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

    private void initHelpButton() {
        helpButton = ButtonFactory.createTextButton("?", 30, 30, new Color(74/255.0f,143/255.0f,231/255.0f,1), Color.WHITE, assets.fonts.verdana_30);
        helpButton.setPosition(GameConstants.WIDTH - helpButton.getWidth() - 15, GameConstants.HEIGHT - helpButton.getHeight() - 5);
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                helpVisible = !helpVisible;
                showHelpPanel(helpVisible);
            }
        });
    }

    private void initHelpPanelImage() {
        Pixmap pixmap = new Pixmap(250, 90, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(146/255.0f, 167/255.0f, 206/255.0f, 0.9f));
        pixmap.fill();
        pixmap.setColor(new Color(45/255.0f, 51/255.0f, 63/255.0f, 0.9f));
        for (int i = 0; i < pixmap.getWidth(); i++) {
            for (int j = 0; j < pixmap.getHeight(); j++) {
                if (i < 5 || j < 5 || i >= pixmap.getWidth() - 5 || j >= pixmap.getHeight() - 5) {
                    pixmap.drawPixel(i, j);
                }
            }
        }

        helpPanelImage = new Image(new Texture(pixmap));
        pixmap.dispose();

        helpPanelImage.setPosition(GameConstants.WIDTH/2 - helpPanelImage.getWidth()/2,
                GameConstants.HEIGHT/2 + helpPanelImage.getHeight()/2);
        helpPanelImage.setVisible(false);
    }

    private void initHelpLabel() {
        String text = "CONTROLS\n" +
                "- Drag to move\n" +
                "- Double tap to rename\n";
        helpLabel = LabelFactory.createLabel(text, assets.fonts.verdana_16, Color.BLACK);
        helpLabel.setPosition(helpPanelImage.getX() + helpPanelImage.getWidth()/2 - helpLabel.getWidth()/2,
                helpPanelImage.getY() + helpPanelImage.getHeight()/2 - helpLabel.getHeight()/2 - 10);
        helpLabel.setVisible(false);
    }

    private void showHelpPanel(boolean visible) {
        helpPanelImage.setVisible(visible);
        helpLabel.setVisible(visible);
    }
}
