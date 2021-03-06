package ics.yudzeen.abstracto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import ics.yudzeen.abstracto.Abstracto;

public class DesktopLauncher {

	private static boolean REBUILD_ATLAS = true;
	private static boolean DRAW_DEBUG_OUTLINE = false;

	public static void main (String[] arg) {

		if(REBUILD_ATLAS) {
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = DRAW_DEBUG_OUTLINE;
			TexturePacker.processIfModified(settings, "../../desktop/assets-raw/images/ui", "images", "ui.pack");
			TexturePacker.processIfModified(settings, "../../desktop/assets-raw/images/simulator", "images", "simulator.pack");
			TexturePacker.processIfModified(settings, "../../desktop/assets-raw/images/game", "images", "game.pack");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 850;
		config.height = 480;
		new LwjglApplication(new Abstracto(null), config);
	}
}
