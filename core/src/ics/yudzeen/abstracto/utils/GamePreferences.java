package ics.yudzeen.abstracto.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Game persistence
 */

public class GamePreferences {

    public static final String TAG = GamePreferences.class.getName();
    public static final GamePreferences instance = new GamePreferences();

    public String character;
    public String name;
    public boolean introDone;
    //public boolean stackSchoolDialogueDone;
    //public boolean stackArcadeDialogueDone;
    //public boolean stackArenaDialogueDone;
    //public boolean queueSchoolDialogueDone;
    //public boolean queueArcadeDialogueDone;
    //public boolean queueArenaDialogueDone;

    private Preferences prefs;

    // singleton: prevent instantiation from other classes
    private GamePreferences() {
        prefs = Gdx.app.getPreferences(GameConstants.PREFERENCES);
    }

    public void load() {
        character = prefs.getString("character", "");
        name = prefs.getString("name", "");
        introDone = prefs.getBoolean("introDone", false);
        //stackSchoolDialogueDone = prefs.getBoolean("stackSchoolDialogueDone", false);
        //stackArcadeDialogueDone = prefs.getBoolean("stackArcadeDialogueDone", false);
        //stackArenaDialogueDone = prefs.getBoolean("stackArenaDialogueDone", false);

        //queueSchoolDialogueDone = prefs.getBoolean("queueSchoolDialogueDone", false);
        //queueArcadeDialogueDone = prefs.getBoolean("queueArcadeDialogueDone", false);
        //queueArenaDialogueDone = prefs.getBoolean("queueArenaDialogueDone", false);
    }

    public void save() {
        prefs.putString("character", character);
        prefs.putString("name", name);
        prefs.putBoolean("introDone", introDone);
        //prefs.putBoolean("stackSchoolDialogueDone", stackSchoolDialogueDone);
        //prefs.putBoolean("stackArcadeDialogueDone", stackArcadeDialogueDone);
        //prefs.putBoolean("stackArenaDialogueDone", stackArenaDialogueDone);

        //prefs.putBoolean("queueSchoolDialogueDone", queueSchoolDialogueDone);
        //prefs.putBoolean("queueArcadeDialogueDone", queueArcadeDialogueDone);
        //prefs.putBoolean("queueArenaDialogueDone", queueArenaDialogueDone);

        prefs.flush();
    }

}
