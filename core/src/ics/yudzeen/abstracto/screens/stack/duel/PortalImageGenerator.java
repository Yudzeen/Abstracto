package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

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

    public PortalImageGenerator(DuelScreen gameScreen, List<String> pushPopList) {
        this.gameScreen = gameScreen;
        this.pushPopList = pushPopList;
        init();
    }

    private void init() {
        initTextureList();
    }

    private void initTextureList() {
        portalImageList = new ArrayList<>();
        for (String s : pushPopList) {
            if (s.equals("PUSH")) {
                portalImageList.add(new PortalImage(gameScreen, "PUSH"));
            }
            else {
                portalImageList.add(new PortalImage(gameScreen, "POP"));
            }
        }
    }

    public List<PortalImage> generatePortalImageList() {
        return portalImageList;
    }

}
