package com.phonepe.scorecard.beans;

import java.util.List;

public class Innings {
    List<Over> overs;
    int dismissals;

    public List<Over> getOvers() {
        return overs;
    }

    public void setOvers(List<Over> overs) {
        this.overs = overs;
    }

    public int getDismissals() {
        return dismissals;
    }

    public void setDismissals(int dismissals) {
        this.dismissals = dismissals;
    }

    
    
}
