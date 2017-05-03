package ics.yudzeen.abstracto.screens.queue.games.processscheduling;

import com.badlogic.gdx.Gdx;

/**
 * Process scheduling controller
 */

class GameController {

    static final String TAG = GameController.class.getName();

    static final int TIME_LIMIT = 45;

    float timeElapsed;
    String readySetGoText;

    float timer;

    boolean gameStarted;
    boolean gameWin;
    boolean gameOver;

    GameController() {
        init();
    }

    private void init() {
        timer = TIME_LIMIT;
    }

    void update(float delta) {
        if(gameStarted) {
            updateTimer(delta);
        }
        else {
            updateReadySetGo(delta);
        }
    }

    private void updateReadySetGo(float delta) {
        timeElapsed += delta;
        if (timeElapsed < 1) {
            readySetGoText = "Ready";
        }
        else if (timeElapsed < 2) {
            readySetGoText = "Set";
        }
        else if (timeElapsed < 3) {
            readySetGoText = "QUEUE!";
        }
        else {
            if(!gameStarted) {
                gameStarted = true;
                Gdx.app.debug(TAG, "Game Started.");
            }
        }
    }

    private void updateTimer(float delta) {
        if(timer > 0) {
            timer = timer - delta;
        }
        else {
            timer = 0.0f;
            gameOver = true;
            gameWin = false;
        }
    }
}
