package org.code.model;

import java.util.Random;

public class Dice {
    private final Random random = new Random();
    public Integer roll() {
        return random.nextInt(6) + 1;
    }
}
