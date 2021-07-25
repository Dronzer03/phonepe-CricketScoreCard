package com.phonepe.scorecard.beans;

public class Ball {
    int runs;
    boolean four;
    boolean six;
    boolean bye;
    boolean legBye;
    boolean wide;
    boolean noBall;
    boolean wicket;
    
    public int getRuns() {
        return runs;
    }
    public void setRuns(int runs) {
        this.runs = runs;
    }
    public boolean isFour() {
        return four;
    }
    public void setFour(boolean four) {
        this.four = four;
    }
    public boolean isSix() {
        return six;
    }
    public void setSix(boolean six) {
        this.six = six;
    }
    public boolean isBye() {
        return bye;
    }
    public void setBye(boolean bye) {
        this.bye = bye;
    }
    public boolean isLegBye() {
        return legBye;
    }
    public void setLegBye(boolean legBye) {
        this.legBye = legBye;
    }
    public boolean isWide() {
        return wide;
    }
    public void setWide(boolean wide) {
        this.wide = wide;
    }
    public boolean isNoBall() {
        return noBall;
    }
    public void setNoBall(boolean noBall) {
        this.noBall = noBall;
    }
    public boolean isWicket() {
        return wicket;
    }
    public void setWicket(boolean wicket) {
        this.wicket = wicket;
    }
    @Override
    public String toString() {
        return "Ball [bye=" + bye + ", four=" + four + ", legBye=" + legBye + ", noBall=" + noBall + ", runs=" + runs
                + ", six=" + six + ", wicket=" + wicket + ", wide=" + wide + "]";
    }

    
}
