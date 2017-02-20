package ics.yudzeen.abstracto.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Utility for managing assets
 */

public class Assets implements AssetErrorListener, Disposable {

    public static final String TAG = Assets.class.getName();

    private static Assets instance = new Assets();

    private AssetManager assetManager;

    public AssetButtons buttons;
    public AssetImages images;

    private Assets() { }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
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

        // Title Screen
        public AtlasRegion start;
        public AtlasRegion about;

        // Home Screen
        public AtlasRegion profile;
        public AtlasRegion achievements;
        public AtlasRegion map;

        // ADT Screen
        public AtlasRegion guru;
        public AtlasRegion simulator;
        public AtlasRegion townplaza;
        public AtlasRegion monsterfight;

        public AtlasRegion forward_arrow;
        public AtlasRegion back;
        public AtlasRegion town_icon;

        public AssetButtons(TextureAtlas atlas) {
            start = atlas.findRegion("button_start");
            about = atlas.findRegion("button_about");
            profile = atlas.findRegion("button_profile");
            achievements = atlas.findRegion("button_achievements");
            map = atlas.findRegion("world_map");
            guru = atlas.findRegion("button_guru");
            simulator = atlas.findRegion("button_simulator");
            townplaza = atlas.findRegion("button_townplaza");
            monsterfight = atlas.findRegion("button_monsterfight");

            town_icon = atlas.findRegion("town_icon");
        }
    }

    public class AssetImages {

        public AtlasRegion title_main;
        public AtlasRegion title_map;
        public AtlasRegion character;

        public AtlasRegion location_home;
        public AtlasRegion location_stack;
        public AtlasRegion location_queue;

        public AtlasRegion blank_map;

        public AssetImages(TextureAtlas atlas) {
            title_main = atlas.findRegion("title_main");
            title_map = atlas.findRegion("title_map");
            character = atlas.findRegion("character");
            location_home = atlas.findRegion("location_home");
            location_stack = atlas.findRegion("location_stackville");
            location_queue = atlas.findRegion("location_queuecity");

            blank_map = atlas.findRegion("blank_map");
        }
    }
}
