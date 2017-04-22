package ics.yudzeen.abstracto.screens.stack.duel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Push pop randomizer
 */

class PushPopRandomizer {

    private Random random = new Random();

    List<String> getRandomizedPushPop(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(random.nextBoolean() ? "PUSH" : "POP");
        }
        return list;
    }

}
