package ics.yudzeen.abstracto.screens.queue.duel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

import ics.yudzeen.abstracto.utils.Assets;
import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Portal that shows images of push and pop
 */

class Portal extends Actor {

    static final String TAG = Portal.class.getName();

    private DuelScreen gameScreen;
    private Assets assets;

    private TextureAtlas.AtlasRegion portalTexture;

    private List<String> enqueueDequeueList;
    private List<PortalImage> portalImageList;
    private int currentIndex;

    private float glowTimer;
    private float glowDuration;
    private Color glowTint;
    private boolean glow = false;

    private boolean newPortal = true;

    public Portal(DuelScreen gameScreen, List<String> enqueueDequeueList) {
        this.enqueueDequeueList = enqueueDequeueList;
        this.gameScreen = gameScreen;
        init();
    }

    private void init() {
        assets = gameScreen.getAssets();
        glowTint = Color.WHITE;
        currentIndex = 0;
        initPortalTexture();
        initPortalImageList();
    }

    private void initPortalTexture() {
        portalTexture = assets.games.portal;
        setWidth(portalTexture.getRegionWidth());
        setHeight(portalTexture.getRegionHeight());
    }

    private void initPortalImageList() {
        PortalImageGenerator portalImageGenerator = new PortalImageGenerator(gameScreen, enqueueDequeueList);
        portalImageList = portalImageGenerator.generatePortalImageList();
        for (PortalImage portalImage : portalImageList) {
            portalImage.setPosition(GameConstants.WIDTH/2 - portalImage.getWidth()/2, getY() + 40);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(portalTexture, getX(), getY());
        if(newPortal) {
            if(glow) {
                batch.setColor(glowTint);
            }
            portalImageList.get(currentIndex).draw(batch, parentAlpha);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(glow) {
            glow(delta);
        }
    }

    private void glow(float delta) {
        glowTimer += delta;
        if (glowTimer > 0.5) {
            glowTimer = 0;
        }
        else if(glowTimer > 0.25) {
            glowTint = Color.WHITE;
        }
        else if (glowTimer > 0){
            glowTint = Color.YELLOW;
        }

        glowDuration+=delta;
        if(glowDuration > 1.5) {
            glowDuration = 0;
            glow = false;
        }
    }

    public void nextPortalImage() {
        glow = true;
        currentIndex++;
        portalImageList.get(currentIndex).addAction(
                Actions.sequence(Actions.alpha(0),Actions.fadeIn(2.0f))
        );
    }
}
