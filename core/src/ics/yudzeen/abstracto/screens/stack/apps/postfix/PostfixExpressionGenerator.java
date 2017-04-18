package ics.yudzeen.abstracto.screens.stack.apps.postfix;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * This class generates postfix expressions.
 */

class PostfixExpressionGenerator {

    public static final String TAG = PostfixExpressionGenerator.class.getName();

    private static final String[] OPERATORS = new String[] {"+", "-", "*", "/"};
    private static final String[] OPERANDS = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final int DEFAULT_SIZE = 9;
    private static final String TEMP_OPERAND = "_";

    private List<String> allPossibleExpressionsList;

    public PostfixExpressionGenerator() {
        init();
    }

    private void init() {
        allPossibleExpressionsList = generatePossibleExpressions(DEFAULT_SIZE);
    }

    private List<String> generatePossibleExpressions(int expressionSize) {
        List<String> possibleExpressionsList = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        int sizeOfOperands = Math.round((expressionSize+1)/2);
        stack.push("");
        while (!stack.isEmpty()) {
            String partialExpression = stack.pop();

            if(partialExpression.length() == expressionSize) {
                possibleExpressionsList.add(partialExpression);
                continue;
            }

            int numOperandsConsumed = 0;
            for (char c: partialExpression.toCharArray()) {
                if(c == TEMP_OPERAND.charAt(0)) {
                    numOperandsConsumed++;
                }
            }

            if (2*numOperandsConsumed-partialExpression.length() > 1) {
                for(String s: OPERATORS) {
                    stack.push(partialExpression + s);
                }
            }

            if(numOperandsConsumed != sizeOfOperands) {
                stack.push(partialExpression + TEMP_OPERAND);
            }
        }
        return possibleExpressionsList;
    }

    public List<String> getAllPossibleExpressionsList() {
        return allPossibleExpressionsList;
    }

    public List<String> getRandomExpressions(int num) {
        List<String> randomExpressionsList = new ArrayList<String>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            String expression = String.copyValueOf(allPossibleExpressionsList.get(random.nextInt(allPossibleExpressionsList.size())).toCharArray());
            for (int j = 0; j < Math.round((expression.length() + 1) / 2); j++) {
                expression = expression.replaceFirst(TEMP_OPERAND, OPERANDS[random.nextInt(OPERANDS.length)]);
            }
            randomExpressionsList.add(expression);
        }
        replaceDivisionByZeroExpressions(randomExpressionsList);
        return randomExpressionsList;
    }

    public List<String> generateAnswers(List<String> expression) {
        List<String> answersList = new ArrayList<String>();
        for (int i = 0; i < expression.size(); i++) {
            String s = expression.get(i);
            if(arrayContains(OPERANDS, s)) {
                answersList.add("PUSH");
            }
            else if(arrayContains(OPERATORS, s)) {
                answersList.add("POP");
                answersList.add("POP");
                if(i < expression.size()-1) {
                    answersList.add("PUSH");
                }
            }
            else {
                Gdx.app.debug(TAG, "Invalid Expression.");
            }
        }
        return answersList;
    }

    public boolean arrayContains(String[] array, String s) {
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces division by zero expressions
     * @param randomExpressionsList
     */
    private void replaceDivisionByZeroExpressions(List<String> randomExpressionsList) {
        for (int i = 0; i < randomExpressionsList.size(); i++) {
            String expression = randomExpressionsList.get(i);
            while(!isSolvable(expression)) {
                Gdx.app.debug(TAG, "Division by zero, replacing: " + expression);
                expression = getRandomExpressions(1).get(0);
                Gdx.app.debug(TAG, "with " + expression);
            }
        }
    }

    /**
     * To catch division by zero
     * @param expression
     * @return
     */
    private boolean isSolvable(String expression) {
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < expression.length(); i++) {
            String s = Character.toString(expression.charAt(i));
            if(arrayContains(OPERANDS, s)) {
                stack.push(s);
            }
            else {
                int b = Integer.parseInt(stack.pop());
                int a = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        stack.push(Integer.toString(a+b));
                        break;
                    case "-":
                        stack.push(Integer.toString(a-b));
                        break;
                    case "*":
                        stack.push(Integer.toString(a*b));
                        break;
                    case "/":
                        if(b==0){
                            return false;
                        }
                        stack.push(Integer.toString(a/b));
                        break;

                }
            }
        }
        return true;
    }
}
