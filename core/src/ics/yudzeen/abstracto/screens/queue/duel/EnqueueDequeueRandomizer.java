package ics.yudzeen.abstracto.screens.queue.duel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Enqueue dequeue randomizer
 */

public class EnqueueDequeueRandomizer {

    private Random random = new Random();

    List<String> getRandomizedEnqueueDequeue(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(random.nextBoolean() ? "ENQUEUE" : "DEQUEUE");
        }
        return list;
    }

}
