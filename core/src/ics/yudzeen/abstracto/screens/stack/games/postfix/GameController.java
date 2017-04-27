package ics.yudzeen.abstracto.screens.stack.games.postfix;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controls the game
 */

class GameController {

    public static final String TAG = GameController.class.getName();

    public static final int MAX_LIVES = 3;
    public static final int TIME_LIMIT = 60; // seconds
    public static final int NUM_EXPRESSIONS = 10;

    public static final int POP_FAILED = 0;
    public static final int POP_1 = 1;
    public static final int POP_2 = 2;

    private PostfixExpressionGameScreen gameScreen;

    private int lives;
    private float time;
    private int expressionsLeft;
    private List<String> expressionsList;

    private float timeElapsed;

    private boolean isGameStarted;

    private String readySetGoText;
    private List<String> answersList;

    private List<String> stack;

    private String[] poppedObjects;
    private int popCounter;
    private List<String> currentExpression;

    private final float popdelay = 0.5f;

    private boolean handleInputs;

    public GameController(PostfixExpressionGameScreen gameScreen) {
        this.gameScreen = gameScreen;
        init();
    }

    private void init() {
        lives = MAX_LIVES;
        time = TIME_LIMIT;
        expressionsLeft = NUM_EXPRESSIONS;
        isGameStarted = false;
        readySetGoText = "Ready";
        PostfixExpressionGenerator pfeGenerator = new PostfixExpressionGenerator();
        expressionsList = pfeGenerator.getRandomExpressions(NUM_EXPRESSIONS);
        currentExpression = getCurrentExpressionAsList();
        answersList = pfeGenerator.generateAnswers(currentExpression);
        stack = new ArrayList<String>();
        popCounter = 2;
        poppedObjects = new String[] {"", ""};
        Gdx.app.debug(TAG, "Expression: " + currentExpression + " Answers: " + answersList);
        handleInputs = true;
    }

    public void update(float delta) {

        if(isGameStarted) {
            if(time > 0) {
                time = time - delta;
            }
            else {
                time = 0.0f;
            }
        }
        else {
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
                isGameStarted = true;
                Gdx.app.debug(TAG, "Game Started.");
            }
        }
    }

    public int getLives() {
        return lives;
    }

    public float getTime() {
        return time;
    }

    public int getExpressionsLeft() {
        return expressionsLeft;
    }

    public boolean onPushPressed() {
        if(handleInputs) {
            if(getAnswer().equals("PUSH")) {
                answersList.remove(0);
                stack.add(currentExpression.remove(0));
                Gdx.app.debug(TAG, "Stack: " + stack.toString());
                Gdx.app.debug(TAG, "Curr exp: " + currentExpression.toString());
                Gdx.app.debug(TAG, "Popped Nodes:" + Arrays.toString(poppedObjects));
                return true;
            }
            else {
                lives--;
                if (lives == 0) {
                    handleInputs = false;
                }
                return false;
            }
        }
        return false;
    }

    public int onPopPressed() {
        if(handleInputs) {
            if(getAnswer().equals("POP")) {
                int popType = POP_1;
                answersList.remove(0);
                poppedObjects[--popCounter] = stack.remove(stack.size()-1);
                if(popCounter==0) {
                    //gameScreen.gameRenderer.removeNode(0);
                    String operation = currentExpression.remove(0);
                    int answer = solveOperation(poppedObjects[0], poppedObjects[1], operation);
                    currentExpression.add(0, Integer.toString(answer));
                    //gameScreen.gameRenderer.addNode(0,answer);
                    poppedObjects[0] = "";
                    poppedObjects[1] = "";
                    popCounter = 2;
                    popType = POP_2;
                }
                Gdx.app.debug(TAG, "Stack: " + stack.toString());
                Gdx.app.debug(TAG, "Curr exp: " + currentExpression.toString());
                Gdx.app.debug(TAG, "Popped Nodes:" + Arrays.toString(poppedObjects));
                return popType;
            }
            else {
                lives--;
                if (lives == 0) {
                    handleInputs = false;
                }
                return POP_FAILED;
            }
        }
        return POP_FAILED;
    }

    public void onPausePressed() {

    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public String getReadySetGoText() {
        return readySetGoText;
    }

    public List<String> getCurrentExpression() {
        return currentExpression;
    }

    public List<String> getCurrentExpressionAsList() {
        char[] charArray = expressionsList.get(expressionsLeft-1).toCharArray();
        List<String> currentExpression = new ArrayList<String>();
        for (char c: charArray) {
            currentExpression.add(Character.toString(c));
        }
        return currentExpression;
    }

    public List<String> getAnswersList() {
        return answersList;
    }

    public String getAnswer() {
        return getAnswersList().get(0);
    }

    public List<String> getStack() {
        return stack;
    }

    private int solveOperation(String a, String b, String operation) {
        int answer;
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        if (operation.equals("+")) {
            answer = x + y;
        }
        else if (operation.equals("-")) {
            answer = x - y;
        }
        else if (operation.equals("*")) {
            answer = x * y;
        }
        else if (operation.equals("/")) {
            answer = x / y;
        }
        else {
            Gdx.app.error(TAG, "Solving Operation Error: Invalid Operation");
            return Integer.MIN_VALUE;
        }
        return answer;
    }

    public boolean hasWin() {
        return currentExpression.size() == 1 && stack.size() == 0;
    }

    public boolean hasLose() {
        return lives == 0 || time == 0;
    }

    public String peekStack() {
        return stack.get(stack.size()-1);
    }

}
