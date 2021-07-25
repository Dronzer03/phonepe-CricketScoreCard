package com.phonepe.scorecard.beans;

public class Player {
    private String name;
    private BattingEffort battingEffort;
    private BowlingEffort bowlingEffort;
    private FieldingEffort fieldingEffort;
    private boolean captain;
    private boolean isSub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BattingEffort getBattingEffort() {
        if(battingEffort==null)
            this.battingEffort = new BattingEffort();
        return battingEffort;
    }

    public void setBattingEffort(BattingEffort battingEffort) {
        this.battingEffort = battingEffort;
    }

    public BowlingEffort getBowlingEffort() {
        if(bowlingEffort==null)
            this.bowlingEffort=new BowlingEffort();
        return bowlingEffort;
    }

    public void setBowlingEffort(BowlingEffort bowlingEffort) {
        this.bowlingEffort = bowlingEffort;
    }

    public FieldingEffort getFieldingEffort() {
        return fieldingEffort;
    }

    public void setFieldingEffort(FieldingEffort fieldingEffort) {
        this.fieldingEffort = fieldingEffort;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public boolean isSub() {
        return isSub;
    }

    public void setSub(boolean sub) {
        isSub = sub;
    }

    @Override
    public String toString() {
        return "Player [battingEffort=" + battingEffort + ", bowlingEffort=" + bowlingEffort + ", captain=" + captain
                + ", fieldingEffort=" + fieldingEffort + ", isSub=" + isSub + ", name=" + name + "]";
    }

    
}
