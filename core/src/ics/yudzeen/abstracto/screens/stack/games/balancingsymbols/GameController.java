package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;

import com.badlogic.gdx.Gdx;

import java.util.List;
import java.util.Stack;

/**
 * Controller for rendering
 */

class GameController {

    static final String TAG = GameController.class.getName();

    static final int MAX_LIVES = 3;
    static final int TIME_LIMIT = 60;    // seconds
    static final int EXPRESSION_COUNT = 5;
    static final int EXPRESSION_LENGTH = 8;

    float timer;
    int lives;

    ExpressionGenerator expressionGenerator;

    List<List<String>> expressionsList;
    List<List<String>> answersList;
    Stack<String> stack;

    String readySetGoText;
    float timeElapsed;

    boolean gameStarted;

    boolean gameWin;
    boolean gameOver;
    boolean handleInputs;

    public GameController() {
        init();
    }

    private void init() {
        timer = TIME_LIMIT;
        lives = MAX_LIVES;
        expressionGenerator = new ExpressionGenerator();
        expressionsList = expressionGenerator.generateRandomExpressionList(EXPRESSION_COUNT, EXPRESSION_LENGTH);
        answersList = expressionGenerator.solveExpressions(expressionsList);
        stack = new Stack<>();
        debugInfo();
        handleInputs = true;
    }


    public void update(float delta) {
        //updateReadySetGo(delta);
        if(!gameOver) {
            updateTimer(delta);
        }
    }

    private void updateTimer(float delta) {
        if(timer > 0) {
            timer = timer - delta;
        }
        else {
            timer = 0.0f;
            gameOver = true;
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
            readySetGoText = "STACK!";
        }
        else {
            if(!gameStarted) {
                gameStarted = true;
                Gdx.app.debug(TAG, "Game Started.");
            }
        }
    }

    List<String> getCurrentExpression() {
        return expressionsList.get(0);
    }

    List<String> getCurrentAnswers() {
        return answersList.get(0);
    }

    boolean onPushPressed() {
        if(handleInputs) {
            if (validateAnswer("PUSH")) {
                String currentString = getCurrentExpression().remove(0);
                stack.push(currentString);
                hasWon();
                debugInfo();
                return true;
            }
            else {
                decreaseLife();
                return false;
            }
        }
        return false;
    }

    boolean onPopPressed() {
        if(handleInputs) {
            if (validateAnswer("POP")) {
                String currentString = getCurrentExpression().remove(0);
                String tos = stack.pop();
                hasWon();
                debugInfo();
                return true;
            }
            else {
                decreaseLife();
                return false;
            }
        }
        return false;
    }

    private boolean validateAnswer(String answer) {
        String correctAnswer = getCurrentAnswers().get(0);
        if (answer.equals(correctAnswer)) {
            getCurrentAnswers().remove(0);
            return true;
        }
        return false;
    }

    private void decreaseLife() {
        lives--;
        if(lives == 0) {
            gameWin = false;
            gameOver = true;
            handleInputs = false;
            Gdx.app.debug(TAG, "You lose.");
        }
    }

    private boolean hasWon() {
        if(getCurrentExpression().isEmpty()) {
            Gdx.app.debug(TAG, "You win.");
            gameWin = true;
            gameOver = true;
            handleInputs = false;
            return true;
        }
        return false;
    }

    private void debugInfo() {
        Gdx.app.debug(TAG, "Current Expression: " + getCurrentExpression());
        Gdx.app.debug(TAG, "Stack: " + stack);
        Gdx.app.debug(TAG, "Answers: " + getCurrentAnswers());
    }
}
