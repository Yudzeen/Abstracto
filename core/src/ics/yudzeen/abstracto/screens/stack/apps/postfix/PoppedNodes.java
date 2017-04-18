package ics.yudzeen.abstracto.screens.stack.apps.postfix;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Popped nodes class
 */

class PoppedNodes extends Actor {

    public static final String TAG = PoppedNodes.class.getName();

    private PostfixExpressionGameScreen gameScreen;
    private GameNode[] poppedNodes;
    private GameNode[] tempNodes;

    public PoppedNodes(PostfixExpressionGameScreen gameScreen) {
        this.gameScreen = gameScreen;
        init();
    }

    private void init() {
        initTempNodes();
        initPoppedNodes();
    }

    private void initTempNodes() {
        // initialize placeholder game nodes
        tempNodes = new GameNode[2];
        for (int i = 0; i < tempNodes.length; i++) {
            tempNodes[i] = new GameNode(gameScreen, gameScreen.gameRenderer, "");
            tempNodes[i].setTexture(gameScreen.getAssets().games.gray_circle);
        }
    }

    private void initPoppedNodes() {
        // initialize placeholder game nodes
        poppedNodes = new GameNode[2];

        for (int i = 0; i < poppedNodes.length; i++) {
            poppedNodes[i] = new GameNode(gameScreen, gameScreen.gameRenderer, "");
            poppedNodes[i].setTexture(gameScreen.getAssets().games.gray_circle);
            poppedNodes[i].setVisible(false);
        }

    }

    public GameNode[] getPoppedNodes() {
        return poppedNodes;
    }

    public void setPoppedNodes(GameNode[] poppedNodes) {
        this.poppedNodes = poppedNodes;
    }

    public void setPoppedNode(GameNode node, int index) {
        poppedNodes[index] = node;
    }

    public void merge() {
        poppedNodes[0].setMerging(true);
        poppedNodes[1].setMerging(true);
    }

    public GameNode[] getTempNodes() {
        return tempNodes;
    }
}
