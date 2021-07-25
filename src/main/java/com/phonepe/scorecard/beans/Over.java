package com.phonepe.scorecard.beans;

import java.util.List;

public class Over {
    List<Ball> balls;
    int runs;

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
        this.runs = balls.stream().mapToInt(Ball::getRuns).sum();
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }
}
