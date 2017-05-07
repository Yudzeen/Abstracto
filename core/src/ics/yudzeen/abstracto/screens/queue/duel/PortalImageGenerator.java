package ics.yudzeen.abstracto.screens.queue.duel;

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
    private List<String> enqueueDequeueList;
    private List<PortalImage> portalImageList;
    private List<PortalImage> enqueueList;
    private List<PortalImage> dequeueList;

    private Random random = new Random();

    public PortalImageGenerator(DuelScreen gameScreen, List<String> enqueueDequeueList) {
        this.gameScreen = gameScreen;
        this.enqueueDequeueList = enqueueDequeueList;
        init();
    }

    private void init() {
        initEnqueueList();
        initPopList();
        initTextureList();
    }

    private void initTextureList() {
        portalImageList = new ArrayList<>();
        for (String s : enqueueDequeueList) {
            if (s.equals("ENQUEUE")) {
                portalImageList.add(enqueueList.get(random.nextInt(enqueueList.size())));
            }
            else {
                portalImageList.add(dequeueList.get(random.nextInt(dequeueList.size())));
            }
        }
    }

    private void initEnqueueList() {
        enqueueList = new ArrayList<>();
        Gdx.app.debug(TAG, "" + gameScreen.getAssets().games.portal_push1);
        enqueueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_enqueue1));
        enqueueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_enqueue2));
        enqueueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_enqueue3));

    }

    private void initPopList() {
        dequeueList = new ArrayList<>();
        dequeueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_dequeue1));
        dequeueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_dequeue2));
        dequeueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_dequeue3));
        dequeueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_dequeue4));
        dequeueList.add(new PortalImage(gameScreen, "", gameScreen.getAssets().games.portal_dequeue5));

    }

    public List<PortalImage> generatePortalImageList() {
        return portalImageList;
    }

}
