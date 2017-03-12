package ics.yudzeen.abstracto.screens.objects;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.utils.Align;

import ics.yudzeen.abstracto.ui.TextInputAdapter;

/**
 * Created by Yujin on 07/03/2017.
 */

public class Node extends ImageTextButton {


    private NodeTextInputAdapter textInputAdapter;

    public Node(String text, ImageTextButtonStyle style) {
        super(text, style);
        getLabel().setAlignment(Align.center);
        textInputAdapter = new NodeTextInputAdapter(this);
    }

    public NodeTextInputAdapter getTextInputAdapter() {
        return textInputAdapter;
    }

    public class NodeTextInputAdapter extends TextInputAdapter {

        Node node;

        public NodeTextInputAdapter(Node node) {
            this.node = node;
        }

        @Override
        public void input(String text) {
            node.setText(text);
        }
    }




}
