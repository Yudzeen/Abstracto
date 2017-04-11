package ics.yudzeen.abstracto.screens.stack.postfix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yujin on 30/03/2017.
 */

public class Stack {

    public static final int DEFAULT_SIZE = 5;

    private int size;
    private List<String> stack;


    public Stack() {
        init();
    }

    private void init() {
        stack = new ArrayList<String>();
        size = DEFAULT_SIZE;
    }

    public boolean push(String s) {
        if(stack.size() < DEFAULT_SIZE) {
            stack.add(stack.size(), s);
            return true;
        }
        else {
            return false;
        }
    }

    public String pop() {
        if(stack.size() > 0) {
            String item = stack.remove(stack.size()-1);
            return item;
        }
        else {
            return null;
        }
    }

    public int size() {
        return stack.size();
    }
}
