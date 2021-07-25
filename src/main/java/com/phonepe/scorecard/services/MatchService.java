package com.phonepe.scorecard.services;

import com.phonepe.scorecard.beans.Match;
import com.phonepe.scorecard.beans.Player;
import com.phonepe.scorecard.beans.Team;

public interface MatchService {
    public void setOvers(int overs);
    public void setTeamSize(int teamSize);
    public void setTeam(int inputTeam, Team team);
    public Match getMatch();
    public Player getBowler(int inningsNumber, String bowlerName);
    public Player manOfTheMatch();
    public void printScoreCard();
}
