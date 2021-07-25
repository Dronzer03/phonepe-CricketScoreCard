package com.phonepe.scorecard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.phonepe.scorecard.beans.Ball;
import com.phonepe.scorecard.beans.Innings;
import com.phonepe.scorecard.beans.Match;
import com.phonepe.scorecard.beans.Over;
import com.phonepe.scorecard.beans.Player;
import com.phonepe.scorecard.beans.Team;
import com.phonepe.scorecard.config.appConfig;
import com.phonepe.scorecard.services.MatchService;
import com.phonepe.scorecard.services.ScorerService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Start Match" );

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(appConfig.class);
        MatchService matchService = context.getBean(MatchService.class);
        ScorerService scorerService = context.getBean(ScorerService.class);

        Scanner input = new Scanner(System.in);

        //Input No of overs
        System.out.print("Enter number of Overs:");
        int overs = input.nextInt();

        //Input TeamSize
        System.out.print("Enter number of players in each team:");
        int teamSize = input.nextInt();

        matchService.setOvers(overs);
        matchService.setTeamSize(teamSize);

        Match match = matchService.getMatch();
        
        //Input Teams
        System.out.println();
        Team team1 = new Team();
        System.out.println("Enter name for Team 1");
        input.nextLine();
        String team1Name = input.nextLine();
        team1.setTeamName(team1Name);
        System.out.println("Enter players for Team 1");
        List<Player> team1Players = new ArrayList<>();
        for(int i=0;i<teamSize;i++){
            Player player = new Player();
            String name = input.nextLine();
            player.setName(name);
            team1Players.add(player);
        }
        team1.setPlayers(team1Players);
        match.setTeam1(team1);

        System.out.println();
        Team team2 = new Team();
        System.out.println("Enter name for Team 2");
        String team2Name = input.nextLine();
        team2.setTeamName(team2Name);
        System.out.println("Enter players for Team 2");
        List<Player> team2Players = new ArrayList<>();
        for(int i=0;i<teamSize;i++){
            Player player = new Player();
            String name = input.nextLine();
            player.setName(name);
            team2Players.add(player);
        }
        team2.setPlayers(team2Players);
        match.setTeam2(team2);

        //Start Scoring
        System.out.println("Starting Scoring for Match");
        boolean matchEnd = false;
        for(int inn=1;inn<=2;inn++){
            System.out.println("Starting Innings: "+inn);
            Innings innings = new Innings();
            scorerService.setInnings(innings, inn);
            List<Over> overList = new ArrayList<>();
            innings.setOvers(overList);
            int dismissals = 0;
            boolean inningsEnd = false;
            List<Player> battingPlayersList = match.getTeam(inn).getPlayers();
            int bowlInn=1;
            if(inn==1)
                bowlInn=2;
            int strikePlayer = 0;
            int nonStrikePlayer = 1;
            int currentScore = 0;
            for(int overNo=0; overNo<overs;overNo++){
                System.out.println("Starting over "+ (overNo+1));
                System.out.print("Enter Bowler Name : ");
                String bowlerName = input.nextLine();
                Player bowler = matchService.getBowler(bowlInn, bowlerName);
                int balls = 0;
                Over over = new Over();
                List<Ball> ballList = new ArrayList<>();
                while(balls<6){
                    Ball ball = new Ball();
                    String ballInput = input.nextLine();
                    //Switch Case
                    Player player = battingPlayersList.get(strikePlayer);
                    switch(ballInput){
                        case "0":
                            balls++;
                            ball.setRuns(0);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            break;
                        case "1":
                            balls++;
                            ball.setRuns(1);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            player.getBattingEffort().setRuns(player.getBattingEffort().getRuns()+1);
                            strikePlayer = swapStrike(nonStrikePlayer, nonStrikePlayer = strikePlayer);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + 1);
                            break;
                        case "2":
                            balls++;
                            ball.setRuns(2);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            player.getBattingEffort().setRuns(player.getBattingEffort().getRuns()+2);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + 2);
                            break;
                        case "3":
                            balls++;
                            ball.setRuns(3);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            player.getBattingEffort().setRuns(player.getBattingEffort().getRuns()+3);
                            strikePlayer = swapStrike(nonStrikePlayer, nonStrikePlayer = strikePlayer);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + 3);
                            break;
                        case "4":
                            balls++;
                            ball.setFour(true);
                            ball.setRuns(4);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            player.getBattingEffort().setRuns(player.getBattingEffort().getRuns()+4);
                            player.getBattingEffort().setFours(player.getBattingEffort().getFours()+1);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + 4);
                            break;
                        case "5":
                            balls++;
                            ball.setRuns(5);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            player.getBattingEffort().setRuns(player.getBattingEffort().getRuns()+5);
                            strikePlayer = swapStrike(nonStrikePlayer, nonStrikePlayer = strikePlayer);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + 5);
                            break;
                        case "6":
                            balls++;
                            ball.setSix(true);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            player.getBattingEffort().setRuns(player.getBattingEffort().getRuns()+6);
                            ball.setRuns(6);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + 6);
                            break;
                        case "Wd":
                            ball.setWide(true);
                            ball.setRuns(1);
                            System.out.print("Extra runs conceded:");
                            int wides = input.nextInt();
                            wides++;
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + wides);
                            break;
                        case "Nb":
                            ball.setNoBall(true);
                            ball.setRuns(1);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            System.out.print("Extra runs conceded:");
                            int nbs = input.nextInt();
                            nbs++;
                            bowler.getBowlingEffort().setRunsConceded(bowler.getBowlingEffort().getRunsConceded() + nbs);
                            break;
                        case "W":
                            balls++;
                            ball.setRuns(0);
                            player.getBattingEffort().setBallsFaced(player.getBattingEffort().getBallsFaced()+1);
                            bowler.getBowlingEffort().setBallsBowled(bowler.getBowlingEffort().getBallsBowled() + 1);
                            bowler.getBowlingEffort().setWickets(bowler.getBowlingEffort().getWickets() + 1);
                            dismissals++;
                            if(dismissals == (teamSize -1)){
                                inningsEnd = true;
                                System.out.println("All wickets down! End of the innings...");
                            }
                            else
                                strikePlayer = Math.max(strikePlayer, nonStrikePlayer)+1;
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    ballList.add(ball);
                    if(inningsEnd){
                        break;
                    }
                    if(inn==2){
                        int target = scorerService.getTotalScore(scorerService.getInnings(1)) + 1;
                        currentScore =  currentScore + ball.getRuns();
                        if(currentScore>=target){
                            matchEnd = true;
                            System.out.println(match.getTeam2().getTeamName()+" has won the match by "+(teamSize-1-dismissals)+" wickets.");
                            break;
                        }
                            
                    }
                }
                overList.add(over);
                over.setBalls(ballList);
                printScoreCard(battingPlayersList.get(strikePlayer),battingPlayersList.get(nonStrikePlayer));
                printBowlingFigures(bowler);
                strikePlayer = swapStrike(nonStrikePlayer, nonStrikePlayer = strikePlayer);
                
                System.out.println("Total Score:"+scorerService.getTotalScore(innings)+"/"+dismissals);
                if(matchEnd || inningsEnd)
                    break;
            }
            int team1Score = scorerService.getTotalScore(scorerService.getInnings(1)); 
            if(currentScore<team1Score && inn==2){
                System.out.println(match.getTeam1().getTeamName()+" has won the match by "+ (team1Score-currentScore) + " runs.");
            }
            else if(currentScore==team1Score && inn==2){
                System.out.println("It's a Tie!!!");
            }
        }
        System.out.println("Man of the Match: "+ matchService.manOfTheMatch().getName());
        matchService.printScoreCard();
    }

    public static int swapStrike(int... args) {
        return args[0];
    }

    public static void printScoreCard(Player player1, Player player2){
        System.out.println(player1.getName() + ":: "+player1.getBattingEffort().getRuns() + 
        "(" + player1.getBattingEffort().getBallsFaced()+")");

        System.out.println(player2.getName() + ":: "+player2.getBattingEffort().getRuns() + 
        "(" + player2.getBattingEffort().getBallsFaced()+")");
    }

    public static void printBowlingFigures(Player bowler){
        int oversBowled = bowler.getBowlingEffort().getBallsBowled() / 6;
        int extraBalls = bowler.getBowlingEffort().getBallsBowled() % 6;
        System.out.println(bowler.getName() + ":: "+ oversBowled+"."+extraBalls+"-"+bowler.getBowlingEffort().getRunsConceded()+"-"+
        bowler.getBowlingEffort().getWickets());
    }
}
