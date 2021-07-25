package com.phonepe.scorecard.serviceImpl;

import java.util.List;

import com.phonepe.scorecard.beans.Match;
import com.phonepe.scorecard.beans.Player;
import com.phonepe.scorecard.beans.Team;
import com.phonepe.scorecard.services.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchServiceImpl implements MatchService{
    @Autowired
    private Match match;

    @Override
    public void setOvers(int overs) {
        // TODO Auto-generated method stub
        match.setNoOfOvers(overs);
    }

    @Override
    public void setTeamSize(int teamSize) {
        // TODO Auto-generated method stub
        match.setTeamSize(teamSize);   
    }

    @Override
    public void setTeam(int inputTeam, Team team) {
        // TODO Auto-generated method stub
        if(inputTeam==1)
            match.setTeam1(team);
        else
            match.setTeam2(team);
    }

    @Override
    public Match getMatch() {
        // TODO Auto-generated method stub
        return match;
    }

    @Override
    public Player getBowler(int inningsNumber, String bowlerName) {
        // TODO Auto-generated method stub
        Team team;
        if(inningsNumber==1)
            team = match.getTeam1();
        else
            team = match.getTeam2();
        List<Player> players = team.getPlayers();
        for(int i=0;i<players.size();i++){
            if(players.get(i).getName().equals(bowlerName))
                return players.get(i);
        }
        return null;
    }

    @Override
    public Player manOfTheMatch() {
        // TODO Auto-generated method stub
        Player player=match.getTeam1().getPlayers().get(0);
        int maxPoints = 0;
        for(int i=0; i < match.getTeamSize(); i++){
            Player currPlayer = match.getTeam1().getPlayers().get(i);
            int pts = 0;
            if(currPlayer.getBattingEffort().getRuns()>0){
                pts += currPlayer.getBattingEffort().getRuns();
                pts += currPlayer.getBattingEffort().getFours();
                pts += currPlayer.getBattingEffort().getSixes() * 2;
            }
            if(currPlayer.getBowlingEffort().getBallsBowled()>0){
                double oversBowled = currPlayer.getBowlingEffort().getBallsBowled()/6;
                double economy = currPlayer.getBowlingEffort().getRunsConceded() / oversBowled;
                if(economy < 6)
                    pts+=5;
                pts += (25* currPlayer.getBowlingEffort().getWickets());
            }
            if(pts>maxPoints)
                player = currPlayer;
        }
        for(int i=0; i < match.getTeamSize(); i++){
            Player currPlayer = match.getTeam2().getPlayers().get(i);
            int pts = 0;
            if(currPlayer.getBattingEffort().getRuns()>0){
                pts += currPlayer.getBattingEffort().getRuns();
                pts += currPlayer.getBattingEffort().getFours();
                pts += currPlayer.getBattingEffort().getSixes() * 2;
            }
            if(currPlayer.getBowlingEffort().getBallsBowled()>0){
                double oversBowled = currPlayer.getBowlingEffort().getBallsBowled()/6;
                double economy = currPlayer.getBowlingEffort().getRunsConceded() / oversBowled;
                if(economy < 6)
                    pts+=5;
                pts += (25* currPlayer.getBowlingEffort().getWickets());
            }
            if(pts>maxPoints)
                player = currPlayer;
        }
        return player;
    }

    @Override
    public void printScoreCard() {
        // TODO Auto-generated method stub
        System.out.println();
        System.out.println("Scorecard:");

        System.out.println();
        System.out.println(match.getTeam1().getTeamName()+" Batting:");
        List<Player> team1Players = match.getTeam1().getPlayers();
        List<Player> team2Players = match.getTeam2().getPlayers();
        for(int i=0;i<match.getTeamSize();i++){
            System.out.println(team1Players.get(i).getName()+" - " + team1Players.get(i).getBattingEffort().getRuns() + "(" + 
            team1Players.get(i).getBattingEffort().getBallsFaced() + ")");
        }

        System.out.println();
        System.out.println(match.getTeam2().getTeamName()+" Bowling:");
        for(int i=0;i<match.getTeamSize();i++){
            int oversBowled = team2Players.get(i).getBowlingEffort().getBallsBowled() / 6;
            int extraBalls = team2Players.get(i).getBowlingEffort().getBallsBowled() % 6;
            System.out.println(team2Players.get(i).getName() + ":: "+ oversBowled+"."+extraBalls+"-"+team2Players.get(i).getBowlingEffort().getRunsConceded()+"-"+
            team2Players.get(i).getBowlingEffort().getWickets());
        }

        System.out.println();
        System.out.println(match.getTeam2().getTeamName()+" Batting:");
        for(int i=0;i<match.getTeamSize();i++){
            System.out.println(team2Players.get(i).getName()+" - " + team2Players.get(i).getBattingEffort().getRuns() + "(" + 
            team2Players.get(i).getBattingEffort().getBallsFaced() + ")");
        }

        System.out.println();
        System.out.println(match.getTeam1().getTeamName()+" Bowling:");
        for(int i=0;i<match.getTeamSize();i++){
            int oversBowled = team1Players.get(i).getBowlingEffort().getBallsBowled() / 6;
            int extraBalls = team1Players.get(i).getBowlingEffort().getBallsBowled() % 6;
            System.out.println(team1Players.get(i).getName() + ":: "+ oversBowled+"."+extraBalls+"-"+team1Players.get(i).getBowlingEffort().getRunsConceded()+"-"+
            team1Players.get(i).getBowlingEffort().getWickets());
        }
    }

}