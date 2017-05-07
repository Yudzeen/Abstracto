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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

        public BitmapFont chalk_20;
        public BitmapFont chalk_40;
        public BitmapFont chalk_30;

        public BitmapFont verdana_14;
        public BitmapFont verdana_16;
        public BitmapFont verdana_20;
        public BitmapFont verdana_30;
        public BitmapFont verdana_40;
        public BitmapFont verdana_50;

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

            FreeTypeFontGenerator verdanaGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/verdana.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter verdanaParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

            verdanaParameter.size = 14;
            verdana_14 = verdanaGenerator.generateFont(verdanaParameter);

            verdanaParameter.size = 16;
            verdana_16 = verdanaGenerator.generateFont(verdanaParameter);

            verdanaParameter.size = 20;
            verdana_20 = verdanaGenerator.generateFont(verdanaParameter);

            verdanaParameter.size = 30;
            verdana_30 = verdanaGenerator.generateFont(verdanaParameter);

            verdanaParameter.size = 40;
            verdana_40 = verdanaGenerator.generateFont(verdanaParameter);

            verdanaParameter.size = 50;
            verdana_50 = verdanaGenerator.generateFont(verdanaParameter);

            verdanaGenerator.dispose();

            FreeTypeFontGenerator chalkGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/chalk.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter chalkParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

            chalkParameter.size = 20;
            chalk_20 = chalkGenerator.generateFont(chalkParameter);

            chalkParameter.size = 40;
            chalk_40 = chalkGenerator.generateFont(chalkParameter);


            chalkParameter.size = 30;
            chalk_30 = chalkGenerator.generateFont(chalkParameter);
            chalkGenerator.dispose();
        }
    }

    public class AssetButtons {

        // Title Screen
        public AtlasRegion start;
        public AtlasRegion about;

        // Home Screen
        public AtlasRegion map;

        // ADT screens
        public AtlasRegion arcade;
        public AtlasRegion arena;
        public AtlasRegion school;

        // School screen
        public AtlasRegion book;
        public AtlasRegion apps;
        public AtlasRegion simulator;


        // World map screen
        public AtlasRegion stack_region;
        public AtlasRegion queue_region;

        // Miscellaneous
        public AtlasRegion back;

        // Stack games
        public AtlasRegion postfix_game;
        public AtlasRegion balancing_game;

        // Stack apps
        public AtlasRegion postfix_apps;
        public AtlasRegion balancing_apps;

        // Queue games
        public AtlasRegion process_game;
        public AtlasRegion cashier_game;

        public AtlasRegion blue_triangle;
        public AtlasRegion play;

        public AssetButtons(TextureAtlas atlas) {
            start = atlas.findRegion("button_start");
            about = atlas.findRegion("button_about");
            map = atlas.findRegion("world_map");
            stack_region = atlas.findRegion("region_stack");
            queue_region = atlas.findRegion("region_queue");

            arcade = atlas.findRegion("arcade");
            arena = atlas.findRegion("arena");
            school = atlas.findRegion("school");

            book = atlas.findRegion("book");
            apps = atlas.findRegion("apps");
            simulator = atlas.findRegion("simulator");

            back = atlas.findRegion("back");

            postfix_game = atlas.findRegion("postfix_game");
            balancing_game = atlas.findRegion("balancing_game");

            postfix_apps = atlas.findRegion("postfix_apps");
            balancing_apps = atlas.findRegion("balancing_apps");

            process_game = atlas.findRegion("process_game");
            cashier_game = atlas.findRegion("cashier_game");

            blue_triangle = atlas.findRegion("blue_triangle");
            play = atlas.findRegion("play");
        }
    }

    public class AssetImages {

        // Title Screen
        public AtlasRegion tree;

        public AtlasRegion title_main;
        public AtlasRegion world_map_title;
        public AtlasRegion character;

        public AtlasRegion location_home;
        public AtlasRegion location_stack;
        public AtlasRegion location_queue;

        public AtlasRegion region_map;

        public AtlasRegion blank_map;
        public AtlasRegion cloud01;
        public AtlasRegion cloud02;
        public AtlasRegion cloud03;

        // background
        public AtlasRegion background_grass;
        public AtlasRegion background_arena;
        public AtlasRegion background_grassland;
        public AtlasRegion background_blackboard;
        public AtlasRegion background_introduction;

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

        // info
        public AtlasRegion stack_chalk;
        public AtlasRegion queue_chalk;
        public AtlasRegion arrow_chalk;

        public AtlasRegion chat_bubble;
        public AtlasRegion controller;

        public AssetImages(TextureAtlas atlas) {
            background_grassland = atlas.findRegion("background_grassland");
            background_arena = atlas.findRegion("background_arena");
            background_grass = atlas.findRegion("background_grass");
            background_blackboard = atlas.findRegion("background_blackboard");
            background_introduction = atlas.findRegion("background_introduction");

            tree = atlas.findRegion("tree");

            male = atlas.findRegion("male");
            female = atlas.findRegion("female");
            teacher = atlas.findRegion("teacher");
            old_guy = atlas.findRegion("old_guy");

            male_mugshot = atlas.findRegion("male_mugshot");
            female_mugshot = atlas.findRegion("female_mugshot");
            teacher_mugshot = atlas.findRegion("teacher_mugshot");
            old_guy_mugshot = atlas.findRegion("old_guy_mugshot");

            region_map = atlas.findRegion("region_map");
            blank_map = atlas.findRegion("blank_map");
            world_map_title = atlas.findRegion("world_map_title");

            title_main = atlas.findRegion("title_main");
            character = atlas.findRegion("character");
            location_home = atlas.findRegion("location_home");
            location_stack = atlas.findRegion("location_stackville");
            location_queue = atlas.findRegion("location_queuecity");

            cloud01 = atlas.findRegion("cloud01");
            cloud02 = atlas.findRegion("cloud02");
            cloud03 = atlas.findRegion("cloud03");

            stack_chalk = atlas.findRegion("stack_chalk");
            queue_chalk = atlas.findRegion("queue_chalk");
            arrow_chalk = atlas.findRegion("arrow_chalk");

            chat_bubble = atlas.findRegion("chat_bubble");
            controller = atlas.findRegion("controller");
        }
    }

    public class AssetSimulator {

        public AtlasRegion add_node;
        public AtlasRegion blank_node;
        public AtlasRegion stack_container;
        public AtlasRegion queue_container;
        public AtlasRegion trash;

        public AssetSimulator(TextureAtlas atlas) {
            add_node = atlas.findRegion("add_node");
            blank_node = atlas.findRegion("blank_node");
            stack_container = atlas.findRegion("stack_container");
            queue_container = atlas.findRegion("queue_container");
            trash = atlas.findRegion("trash");
        }
    }

    public class AssetGames {

        public AtlasRegion heart;
        public AtlasRegion pause;
        public AtlasRegion stopwatch;

        public AtlasRegion push;
        public AtlasRegion pop;

        public AtlasRegion enqueue;
        public AtlasRegion dequeue;

        public AtlasRegion gray_circle;
        public AtlasRegion light_yellow_circle;

        public AtlasRegion retry_icon;
        public AtlasRegion exit_icon;

        public AtlasRegion portal;
        public AtlasRegion blank_portal;
        public AtlasRegion portal_push1;
        public AtlasRegion portal_pop1;
        public AtlasRegion portal_balancing;
        public AtlasRegion portal_postfix;

        public AtlasRegion chip;
        public AtlasRegion chip_light;
        public AtlasRegion process_red;
        public AtlasRegion process_red_light;
        public AtlasRegion process_blue;
        public AtlasRegion process_blue_light;
        public AtlasRegion process_yellow;
        public AtlasRegion process_yellow_light;
        public AtlasRegion process_dark;

        public AtlasRegion cashier;
        public AtlasRegion cashier_light;
        public AtlasRegion woman;
        public AtlasRegion woman_light;
        public AtlasRegion woman_dark;
        public AtlasRegion man;
        public AtlasRegion man_light;
        public AtlasRegion man_dark;
        public AtlasRegion kid;
        public AtlasRegion kid_light;
        public AtlasRegion kid_dark;

        public AssetGames(TextureAtlas atlas) {
            heart = atlas.findRegion("heart");
            pause = atlas.findRegion("pause");
            stopwatch = atlas.findRegion("stopwatch");

            push = atlas.findRegion("push");
            pop = atlas.findRegion("pop");

            enqueue = atlas.findRegion("enqueue");
            dequeue = atlas.findRegion("dequeue");

            gray_circle = atlas.findRegion("gray_circle");
            light_yellow_circle = atlas.findRegion("light_yellow_circle");

            retry_icon = atlas.findRegion("retry_icon");
            exit_icon = atlas.findRegion("exit_icon");

            portal = atlas.findRegion("portal");
            blank_portal = atlas.findRegion("blank_portal");

            portal_push1 = atlas.findRegion("portal_push1");
            portal_pop1 = atlas.findRegion("portal_pop1");

            portal_balancing = atlas.findRegion("portal_balancing");
            portal_postfix = atlas.findRegion("portal_postfix");

            chip = atlas.findRegion("chip");
            chip_light = atlas.findRegion("chip_light");
            process_blue = atlas.findRegion("process_blue");
            process_blue_light = atlas.findRegion("process_blue_light");
            process_red = atlas.findRegion("process_red");
            process_red_light = atlas.findRegion("process_red_light");
            process_yellow = atlas.findRegion("process_yellow");
            process_yellow_light = atlas.findRegion("process_yellow_light");
            process_dark = atlas.findRegion("process_dark");

            cashier = atlas.findRegion("cashier");
            cashier_light = atlas.findRegion("cashier_light");
            woman = atlas.findRegion("woman");
            woman_light = atlas.findRegion("woman_light");
            woman_dark = atlas.findRegion("woman_dark");
            man = atlas.findRegion("man");
            man_light = atlas.findRegion("man_light");
            man_dark = atlas.findRegion("man_dark");
            kid = atlas.findRegion("kid");
            kid_light = atlas.findRegion("kid_light");
            kid_dark = atlas.findRegion("kid_dark");
        }
    }
}
