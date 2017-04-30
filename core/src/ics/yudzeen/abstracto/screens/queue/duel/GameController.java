package ics.yudzeen.abstracto.screens.queue.duel;

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

    EnqueueDequeueRandomizer enqueueDequeueRandomizer;
    List<String> enqueueDequeueList;

    float timeElapsed;
    boolean gameWin;
    boolean gameOver;

    GameController() {
        init();
    }

    private void init() {
        selfHP = 4;
        enemyHP = MAX_HP;
        enqueueDequeueRandomizer = new EnqueueDequeueRandomizer();
        enqueueDequeueList = enqueueDequeueRandomizer.getRandomizedEnqueueDequeue(PUSH_POP_COUNT);
        Gdx.app.debug(TAG, "List: " + enqueueDequeueList);
    }

    void update(float delta) {

    }

    boolean onEnqueuePressed() {
        if(validateAnswer("ENQUEUE")) {
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

    boolean onDequeuePressed() {
        if(validateAnswer("DEQUEUE")) {
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
        return answer.equals(enqueueDequeueList.remove(0));
    }

}
