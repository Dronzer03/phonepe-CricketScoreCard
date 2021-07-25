package com.phonepe.scorecard.serviceImpl;

import com.phonepe.scorecard.beans.Innings;
import com.phonepe.scorecard.beans.ScoreCard;
import com.phonepe.scorecard.services.ScorerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScorerServiceImpl implements ScorerService{
    @Autowired
    ScoreCard scoreCard;

    @Override
    public void setInnings(Innings innings, int inningsNumber) {
        // TODO Auto-generated method stub
        if(inningsNumber==1)
            scoreCard.setFirstInnings(innings);
        else
            scoreCard.setSecondInnings(innings);
    }

    @Override
    public int getTotalScore(Innings innings) {
        // TODO Auto-generated method stub
        return innings.getOvers().stream().mapToInt(over -> over.getRuns()).sum();
    }

    @Override
    public Innings getInnings(int inningsNumber) {
        // TODO Auto-generated method stub
        if(inningsNumber==1)
            return scoreCard.getFirstInnings();
        else
            return scoreCard.getSecondInnings();
    }
}
