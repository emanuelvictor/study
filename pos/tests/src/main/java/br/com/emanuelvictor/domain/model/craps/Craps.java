package br.com.emanuelvictor.domain.model.craps;

import static br.com.emanuelvictor.domain.model.craps.Winner.HOUSE;
import static br.com.emanuelvictor.domain.model.craps.Winner.PLAYER;

public class Craps {

    private Winner winner;
    private int point;
    private boolean firstTurn = true;
    private final Dice firstDice = new Dice();
    private final Dice secondDice = new Dice();

    public boolean isTheEnd() {
        return winner != null;
    }

    public Winner getWinner() {
        return winner;
    }

    public int runDices() {
        final int sum = runTwoDices();
        if (firstTurn) {
            if (sum == 7 || sum == 11)
                winner = PLAYER;
            else if (sum == 2 || sum == 3 || sum == 12)
                winner = HOUSE;
            else
                point = sum;
            firstTurn = false;
        } else {
            if (sum == point)
                winner = PLAYER;
            else if (sum == 7)
                winner = HOUSE;
        }
        return sum;
    }

    public int runTwoDices() {
        return runFirstDice() + runSecondDice();
    }

    int runFirstDice() {
        return firstDice.run();
    }

    int runSecondDice() {
        return secondDice.run();
    }

    public boolean playerIsWinner() {
        return this.winner == PLAYER;
    }

    public boolean houseIsWinner() {
        return this.winner == HOUSE;
    }
}
