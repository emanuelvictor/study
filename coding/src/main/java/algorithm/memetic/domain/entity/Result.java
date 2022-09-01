package algorithm.memetic.domain.entity;

import java.math.BigInteger;
import java.util.List;

public class Result {

//    private List<Result> results;

    private int firstBest;
    private int currentBest;
    private int targetBest;
    private String algorithmName;

    public Result() {
    }

    public Result(int firstBest, int currentBest, int targetBest, String algorithmName) {
        this.firstBest = firstBest;
        this.currentBest = currentBest;
        this.targetBest = targetBest;
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getFirstBest() {
        return firstBest;
    }

    public void setFirstBest(int firstBest) {
        this.firstBest = firstBest;
    }

    public int getCurrentBest() {
        return currentBest;
    }

    public void setCurrentBest(int currentBest) {
        this.currentBest = currentBest;
    }

    public int getTargetBest() {
        return targetBest;
    }

    public void setTargetBest(int targetBest) {
        this.targetBest = targetBest;
    }

    @Override
    public String toString() {
        return firstBest + ";" + currentBest + ";" + targetBest + ";" + algorithmName + ";" + this.getAvg();
    }

    public BigInteger getAvg(){
        if(this.currentBest == 0)
            return BigInteger.ZERO;
        return BigInteger.valueOf(this.targetBest * 100L / this.currentBest);
    }
}
