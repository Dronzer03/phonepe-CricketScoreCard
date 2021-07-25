package com.phonepe.scorecard.services;

import com.phonepe.scorecard.beans.Innings;

public interface ScorerService {
    public void setInnings(Innings innings, int inningsNumber);
    public Innings getInnings(int inningsNumber);
    public int getTotalScore(Innings innings);
}
