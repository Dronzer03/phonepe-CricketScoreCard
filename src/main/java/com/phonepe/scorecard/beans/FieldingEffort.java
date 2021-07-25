package com.phonepe.scorecard.beans;

public class FieldingEffort {
    private int catchesTaken;
    private int stumpings;
    private int runOuts;
    private int runSaved;

    public int getCatchesTaken() {
        return catchesTaken;
    }

    public void setCatchesTaken(int catchesTaken) {
        this.catchesTaken = catchesTaken;
    }

    public int getStumpings() {
        return stumpings;
    }

    public void setStumpings(int stumpings) {
        this.stumpings = stumpings;
    }

    public int getRunOuts() {
        return runOuts;
    }

    public void setRunOuts(int runOuts) {
        this.runOuts = runOuts;
    }

    public int getRunSaved() {
        return runSaved;
    }

    public void setRunSaved(int runSaved) {
        this.runSaved = runSaved;
    }
}
