package ics.yudzeen.abstracto.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Add Class Description
 */

public class Assets implements AssetErrorListener, Disposable {

    public static final String TAG = Assets.class.getName();

    private static Assets instance = new Assets();

    private AssetManager assetManager;

    public AssetButtons buttons;
    public AssetImages images;

    private Assets() {
        init();
    }

    private void init() {
        assetManager = new AssetManager();
        assetManager.setErrorListener(this);

        assetManager.load(GameConstants.TEXTURE_ATLAS_UI, TextureAtlas.class);

        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String asset: assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "Asset: " + asset);
        }

        TextureAtlas atlas = assetManager.get(GameConstants.TEXTURE_ATLAS_UI);
        // Pixel smoothing
        for (Texture texture: atlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        buttons = new AssetButtons(atlas);
        images = new AssetImages(atlas);
    }

    public static Assets getInstance() {
        return instance;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: '" + asset.fileName + "'", throwable);
    }

    public class AssetButtons {

        public final AtlasRegion map;
        public final AtlasRegion forward_arrow;
        public final AtlasRegion back_arrow;
        public final AtlasRegion town_icon;

        public AssetButtons(TextureAtlas atlas) {
            map = atlas.findRegion("world_map");
            forward_arrow = atlas.findRegion("chevron_arrow");
            back_arrow = atlas.findRegion("chevron_arrow");
            back_arrow.flip(true, false);
            town_icon = atlas.findRegion("town_icon");
        }
    }

    public class AssetImages {

        public final AtlasRegion blank_map;

        public AssetImages(TextureAtlas atlas) {
            blank_map = atlas.findRegion("blank_map");
        }
    }
}
