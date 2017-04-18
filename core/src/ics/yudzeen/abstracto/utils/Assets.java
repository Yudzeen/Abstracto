package ics.yudzeen.abstracto.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public AssetSimulator simulator;
    public AssetGames games;
    public AssetFonts fonts;

    private Assets() { }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        assetManager.load(GameConstants.TEXTURE_ATLAS_UI, TextureAtlas.class);
        assetManager.load(GameConstants.TEXTURE_ATLAS_GAME, TextureAtlas.class);
        assetManager.load(GameConstants.TEXTURE_ATLAS_SIMULATOR, TextureAtlas.class);

        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String asset: assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "Asset: " + asset);
        }

        TextureAtlas uiAtlas = assetManager.get(GameConstants.TEXTURE_ATLAS_UI);
        // Pixel smoothing
        for (Texture texture: uiAtlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureAtlas gameAtlas = assetManager.get(GameConstants.TEXTURE_ATLAS_GAME);
        // Pixel smoothing
        for (Texture texture: gameAtlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureAtlas simulatorAtlas = assetManager.get(GameConstants.TEXTURE_ATLAS_SIMULATOR);
        // Pixel smoothing
        for (Texture texture: simulatorAtlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        fonts = new AssetFonts();
        buttons = new AssetButtons(uiAtlas);
        images = new AssetImages(uiAtlas);
        simulator = new AssetSimulator(simulatorAtlas);
        games = new AssetGames(gameAtlas);
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

    public class AssetFonts {
        /**
         * 0.75x of the normal size
         */
        public BitmapFont defaultSmall;
        /**
         * normal sized font
         */
        public BitmapFont defaultNormal;
        /**
         * 2x
         */
        public BitmapFont defaultBig;
        /**
         * 3x
         */
        public BitmapFont defaultVeryBig;
        /**
         * 4x
         */
        public BitmapFont defaultLarge;

        public AssetFonts() {
            defaultSmall = new BitmapFont();
            defaultNormal = new BitmapFont();
            defaultBig = new BitmapFont();
            defaultVeryBig = new BitmapFont();
            defaultLarge = new BitmapFont();

            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.00f);
            defaultVeryBig.getData().setScale(3.00f);
            defaultLarge.getData().setScale(4.00f);

            defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultVeryBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultLarge.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    public class AssetButtons {

        // Title Screen
        public AtlasRegion start;
        public AtlasRegion about;

        // Home Screen
        public AtlasRegion map;

        // ADT screens
        public AtlasRegion applications;
        public AtlasRegion duel;
        public AtlasRegion school;  // unnused
        public AtlasRegion simulator;
        public AtlasRegion book;

        public AtlasRegion forward_arrow;
        public AtlasRegion back;
        public AtlasRegion town_icon;

        public AssetButtons(TextureAtlas atlas) {
            start = atlas.findRegion("button_start");
            about = atlas.findRegion("button_about");
            map = atlas.findRegion("world_map");

            applications = atlas.findRegion("applications");
            duel = atlas.findRegion("duel");
            school = atlas.findRegion("school");
            simulator = atlas.findRegion("simulator");
            book = atlas.findRegion("book");

            town_icon = atlas.findRegion("town_icon");
        }
    }

    public class AssetImages {

        // Title Screen
        public AtlasRegion grassland;
        public AtlasRegion tree;

        public AtlasRegion title_main;
        public AtlasRegion title_map;
        public AtlasRegion character;

        public AtlasRegion location_home;
        public AtlasRegion location_stack;
        public AtlasRegion location_queue;

        public AtlasRegion blank_map;
        public AtlasRegion cloud01;
        public AtlasRegion cloud02;
        public AtlasRegion cloud03;

        // humans
        public AtlasRegion male;
        public AtlasRegion female;
        public AtlasRegion teacher;
        public AtlasRegion old_guy;

        // mugshots
        public AtlasRegion male_mugshot;
        public AtlasRegion female_mugshot;
        public AtlasRegion teacher_mugshot;
        public AtlasRegion old_guy_mugshot;

        public AssetImages(TextureAtlas atlas) {
            grassland = atlas.findRegion("grassland");
            tree = atlas.findRegion("tree");

            male = atlas.findRegion("male");
            female = atlas.findRegion("female");
            teacher = atlas.findRegion("teacher");
            old_guy = atlas.findRegion("old_guy");

            male_mugshot = atlas.findRegion("male_mugshot");
            female_mugshot = atlas.findRegion("female_mugshot");
            teacher_mugshot = atlas.findRegion("teacher_mugshot");
            old_guy_mugshot = atlas.findRegion("old_guy_mugshot");

            title_main = atlas.findRegion("title_main");
            title_map = atlas.findRegion("title_map");
            character = atlas.findRegion("character");
            location_home = atlas.findRegion("location_home");
            location_stack = atlas.findRegion("location_stackville");
            location_queue = atlas.findRegion("location_queuecity");

            blank_map = atlas.findRegion("blank_map");

            cloud01 = atlas.findRegion("cloud01");
            cloud02 = atlas.findRegion("cloud02");
            cloud03 = atlas.findRegion("cloud03");
        }
    }

    public class AssetSimulator {

        public AtlasRegion add_node;
        public AtlasRegion blank_node;
        public AtlasRegion stack_container;
        public AtlasRegion trash;

        public AssetSimulator(TextureAtlas atlas) {
            add_node = atlas.findRegion("add_node");
            blank_node = atlas.findRegion("blank_node");
            stack_container = atlas.findRegion("stack_container");
            trash = atlas.findRegion("trash");
        }
    }

    public class AssetGames {

        public AtlasRegion heart;
        public AtlasRegion pause;
        public AtlasRegion stopwatch;

        public AtlasRegion push;
        public AtlasRegion pop;

        public AtlasRegion gray_circle;
        public AtlasRegion light_yellow_circle;

        public AtlasRegion retry_icon;
        public AtlasRegion exit_icon;

        public AssetGames(TextureAtlas atlas) {
            heart = atlas.findRegion("heart");
            pause = atlas.findRegion("pause");
            stopwatch = atlas.findRegion("stopwatch");

            push = atlas.findRegion("push");
            pop = atlas.findRegion("pop");

            gray_circle = atlas.findRegion("gray_circle");
            light_yellow_circle = atlas.findRegion("light_yellow_circle");

            retry_icon = atlas.findRegion("retry_icon");
            exit_icon = atlas.findRegion("exit_icon");
        }
    }
}
