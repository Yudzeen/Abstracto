package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.Gdx;

import java.util.List;

/**
 * Controller for duel
 */

class GameController {

    static final String TAG = GameController.class.getName();

    static final int MAX_HP = 10;
    static final int PUSH_POP_COUNT = 25;

    int selfHP;
    int enemyHP;

    PushPopRandomizer pushPopRandomizer;
    List<String> pushPopList;

    float timeElapsed;
    boolean gameWin;
    boolean gameOver;

    GameController() {
        init();
    }

    private void init() {
        selfHP = 5;
        enemyHP = MAX_HP;
        pushPopRandomizer = new PushPopRandomizer();
        pushPopList = pushPopRandomizer.getRandomizedPushPop(PUSH_POP_COUNT);
        Gdx.app.debug(TAG, "List: " + pushPopList);
    }

    void update(float delta) {

    }

    boolean onPushPressed() {
        if(validateAnswer("PUSH")) {
            enemyHP--;
            if (enemyHP == 0) {
                win();
            }
            Gdx.app.debug(TAG, "Self HP: " + selfHP + ", Enemy HP: " + enemyHP);
            return true;
        }
        else {
            selfHP--;
            if (selfHP == 0) {
                lose();
            }
            Gdx.app.debug(TAG, "Self HP: " + selfHP + ", Enemy HP: " + enemyHP);
            return false;
        }
    }

    boolean onPopPressed() {
        if(validateAnswer("POP")) {
            enemyHP--;
            if (enemyHP == 0) {
                win();
            }
            Gdx.app.debug(TAG, "Self HP: " + selfHP + ", Enemy HP: " + enemyHP);
            return true;
        }
        else {
            selfHP--;
            if (selfHP == 0) {
                lose();
            }
            Gdx.app.debug(TAG, "Self HP: " + selfHP + ", Enemy HP: " + enemyHP);
            return false;
        }
    }

    void win() {
        gameWin = true;
        gameOver = true;
        Gdx.app.debug(TAG, "You win.");
    }

    void lose() {
        gameWin = false;
        gameOver = true;
        Gdx.app.debug(TAG, "You lose.");
    }

    private boolean validateAnswer(String answer) {
        return answer.equals(pushPopList.remove(0));
    }

}
