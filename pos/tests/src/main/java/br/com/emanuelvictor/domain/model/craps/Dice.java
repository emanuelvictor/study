package br.com.emanuelvictor.domain.model.craps;

import java.util.Random;

public class Dice {

    static final Integer SIDES_OF_DICE = 6;

    private final Random rand = new Random();

    public int run() {
        return rand.nextInt(SIDES_OF_DICE) + 1;
    }
}
