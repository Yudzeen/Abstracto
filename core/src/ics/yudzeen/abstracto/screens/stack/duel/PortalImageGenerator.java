package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates an image for the portal
 */

class PortalImageGenerator {

    static final String TAG = PortalImageGenerator.class.getName();

    private DuelScreen gameScreen;
    private List<String> pushPopList;
    private List<PortalImage> portalImageList;
    private List<PortalImage> pushList;
    private List<PortalImage> popList;

    private Random random = new Random();

    public PortalImageGenerator(DuelScreen gameScreen, List<String> pushPopList) {
        this.gameScreen = gameScreen;
        this.pushPopList = pushPopList;
        init();
    }

    private void init() {
        initPushList();
        initPopList();
        initTextureList();
    }

    private void initTextureList() {
        portalImageList = new ArrayList<>();
        for (String s : pushPopList) {
            if (s.equals("PUSH")) {
                portalImageList.add(pushList.get(random.nextInt(pushList.size())));
            }
            else {
                portalImageList.add(popList.get(random.nextInt(popList.size())));
            }
        }
    }

    private void initPushList() {
        pushList = new ArrayList<>();
        Gdx.app.debug(TAG, "" + gameScreen.getAssets().games.portal_push1);
        pushList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_push1));
        for (int i = 0; i <= 9; i++) {
            PortalImage temp = new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_postfix);
            temp.addNode(Integer.toString(i));
            pushList.add(temp);
        }

        final String[] OPENING_SYMBOLS = {"(", "[", "{"};
        for (String s : OPENING_SYMBOLS) {
            PortalImage temp = new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_balancing);
            temp.addNode(s);
            pushList.add(temp);
        }
    }

    private void initPopList() {
        popList = new ArrayList<>();
        popList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_pop1));

        final String[] OPERATORS = {"+", "-", "*", "/"};
        for (String s : OPERATORS) {
            PortalImage temp = new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_postfix);
            temp.addNode(s);
            popList.add(temp);
        }

        final String[] CLOSING_SYMBOLS = {")", "]", "}"};
        for (String s : CLOSING_SYMBOLS) {
            PortalImage temp = new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_balancing);
            temp.addNode(s);
            popList.add(temp);
        }
    }

    public List<PortalImage> generatePortalImageList() {
        return portalImageList;
    }

}
