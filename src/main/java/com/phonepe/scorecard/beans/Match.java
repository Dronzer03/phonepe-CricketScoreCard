package com.phonepe.scorecard.beans;

import org.springframework.stereotype.Component;

@Component
public class Match {
    private Team team1;
    private Team team2;
    private int noOfOvers;
    private int teamSize;
    private ScoreCard scoreCard;


    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getNoOfOvers() {
        return noOfOvers;
    }

    public void setNoOfOvers(int noOfOvers) {
        this.noOfOvers = noOfOvers;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public Team getTeam(int teamNum){
        if(teamNum==1)
            return this.team1;
        else
            return this.team2;
    }

    @Override
    public String toString() {
        return "Match [noOfOvers=" + noOfOvers + ", scoreCard=" + scoreCard + ", team1=" + team1 + ", team2=" + team2
                + ", teamSize=" + teamSize + "]";
    }

    
}
