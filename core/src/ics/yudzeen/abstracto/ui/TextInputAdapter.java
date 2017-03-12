package ics.yudzeen.abstracto.ui;

import com.badlogic.gdx.Input;

/**
 * Created by Yujin on 11/03/2017.
 */

public class TextInputAdapter implements Input.TextInputListener {

    private String text;

    @Override
    public void input(String text) {
        this.text = text;
    }

    @Override
    public void canceled() {

    }

    public String getText() {
        return text;
    }
}
