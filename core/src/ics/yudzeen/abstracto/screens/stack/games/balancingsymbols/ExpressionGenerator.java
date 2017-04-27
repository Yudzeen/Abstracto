package ics.yudzeen.abstracto.screens.stack.games.balancingsymbols;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Generateor of balancing symbol expressions
 */

class ExpressionGenerator {

    static final String TAG = ExpressionGenerator.class.getName();

    private static final String[] OPENING_SYMBOLS = new String[]{"(", "[", "{"};
    private static final String[] CLOSING_SYMBOLS = new String[]{")", "]", "}"};

    private Random random = new Random();

    /**
     * Generates a list of valid and/or invalid balancing symbol equation
     * @param numExpressions number of expressions to generate
     * @param expressionLength length of the expression to be generated
     * @return list of balancing symbol expressions
     */
    List<List<String>> generateRandomExpressionList(int numExpressions, int expressionLength) {
        List<List<String>> randomExpressionsList = new ArrayList<>();
        for (int i = 0; i < numExpressions; i++) {
            String randomExpression = generateRandomCorrectExpression(expressionLength);
            /*
            boolean invalidate = random.nextBoolean();
            if(invalidate) {
                randomExpressionsList.add(stringToList(randomlyInvalidateExpression(randomExpression)));
            }
            else {
                randomExpressionsList.add(stringToList(randomExpression));
            }*/
            randomExpressionsList.add(stringToList(randomExpression));
        }
        return randomExpressionsList;
    }

    /**
     * Generates a valid balancing symbol expression
     * @param expressionLength length of expression
     * @return balancing symbol expression
     */
    String generateRandomCorrectExpression(int expressionLength) {
        Stack<String> partnerStack = new Stack<>();
        String expression = "";

        while (expression.length() < expressionLength) {
            int openingLength = random.nextInt((expressionLength - expression.length()) / 2) + 1;

            for (int i = 0; i < openingLength; i++) {
                int randomizedIndex = random.nextInt(OPENING_SYMBOLS.length);
                expression += OPENING_SYMBOLS[randomizedIndex];
                partnerStack.push(CLOSING_SYMBOLS[randomizedIndex]);
            }

            while(!partnerStack.isEmpty()) {
                expression += partnerStack.pop();
            }
        }
        return expression;
    }

    /**
     * Tries to invalidate a correct expression, 5/6 success rate
     * @param expression
     * @return
     */
    String randomlyInvalidateExpression(String expression) {
        char[] newExpression = expression.toCharArray();

        // change one random index with a 5/6 chance to be different
        int randomizedIndex = random.nextInt(newExpression.length);
        newExpression[randomizedIndex] = random.nextBoolean() ?
                OPENING_SYMBOLS[random.nextInt(OPENING_SYMBOLS.length)].charAt(0) : CLOSING_SYMBOLS[random.nextInt(CLOSING_SYMBOLS.length)].charAt(0);

        return new String(newExpression);
    }

    List<List<String>> solveExpressions(List<List<String>> expressionsList) {
        List<List<String>> answersList = new ArrayList<>();
        for (List<String> expression: expressionsList) {
            answersList.add(solveExpression(expression));
        }
        return answersList;
    }

    List<String> solveExpression(List<String> expression) {
        List<String> answersList = new ArrayList<>();
        for (String s: expression) {
            if(arrayContains(OPENING_SYMBOLS, s)) {
                answersList.add("PUSH");
            }
            else {
                answersList.add("POP");
            }
        }
        return answersList;
    }

    private boolean arrayContains(String[] arr, String s) {
        for (String str: arr) {
            if(str.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private List<String> stringToList(String s) {
        List<String> list = new ArrayList<>();
        for (char c: s.toCharArray()) {
            list.add(Character.toString(c));
        }
        return list;
    }

    boolean isMatching(String openingSymbol, String closingSymbol) {
        return indexOf(OPENING_SYMBOLS, openingSymbol) == indexOf(CLOSING_SYMBOLS, closingSymbol);
    }

    int indexOf(String[] arr, String s) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }
}
