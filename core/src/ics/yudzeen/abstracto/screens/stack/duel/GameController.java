package ics.yudzeen.abstracto.screens.stack.duel;

import com.badlogic.gdx.Gdx;

import java.util.List;

import ics.yudzeen.abstracto.utils.GameConstants;

/**
 * Controller for duel
 */

class GameController {

    static final String TAG = GameController.class.getName();

    static final int MAX_HP = 10;
    static final int PUSH_POP_COUNT = 25;
    static final int ANSWER_TIME_LIMIT = 5;

    DuelScreen duelScreen;

    int selfHP;
    int enemyHP;

    PushPopRandomizer pushPopRandomizer;
    List<String> pushPopList;

    float timeElapsed;
    boolean gameWin;
    boolean gameOver;

    boolean gameStarted;
    String readySetGoText;

    float answerTimer;

    GameController(DuelScreen duelScreen) {
        this.duelScreen = duelScreen;
        init();
    }

    private void init() {
        selfHP = 4;
        enemyHP = MAX_HP;
        pushPopRandomizer = new PushPopRandomizer();
        pushPopList = pushPopRandomizer.getRandomizedPushPop(PUSH_POP_COUNT);
        Gdx.app.debug(TAG, "List: " + pushPopList);
    }

    void update(float delta) {
        if (!gameStarted) {
            updateReadySetGo(delta);
        }
        else {
            updateAnswerTimer(delta);
        }
    }

    private void updateAnswerTimer(float delta) {
        answerTimer += delta;
        if(answerTimer >= ANSWER_TIME_LIMIT) {
            selfHP--;
            if (selfHP == 0) {
                lose();
            }
            Gdx.app.debug(TAG, "Self HP: " + selfHP + ", Enemy HP: " + enemyHP);
            pushPopList.remove(0);
            duelScreen.gameRenderer.playerHPBar.setCurrentHP(duelScreen.gameRenderer.playerHPBar.getCurrentHP() - 1);
            duelScreen.gameRenderer.tooSlowImage.show(1.5f);
            duelScreen.gameRenderer.portal.nextPortalImage();
            answerTimer = 0;
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
            readySetGoText = "Go!";
        }
        else {
            if(!gameStarted) {
                gameStarted = true;
                Gdx.app.debug(TAG, "Game Started.");
            }
        }
    }

    boolean onPushPressed() {
        answerTimer = 0;
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
        answerTimer = 0;
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
