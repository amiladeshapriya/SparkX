package com.spark.cricketmod.controller.gamemods;

import com.spark.cricketmod.controller.GameController;
import com.spark.cricketmod.models.Player;
import com.spark.cricketmod.models.Team;


import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FiveOverGame extends GameController {

    public static final int NO_OF_OVERS = 5;
    public static final int BALLS_PER_OVER = 3;
    public static final int TOTAL_PLAY_EVENTS = 17;
    public static final double NO_OF_TEAMS = 2;

    @Override
    protected int bat(Team battingTeam, int targetScore, Scanner scanner){
        System.out.println("Team "+battingTeam.getName()+" is batting");
        int totNoOfBalls = NO_OF_OVERS * BALLS_PER_OVER;
        Player player = battingTeam.getNewPlayer();
        Random rand = new Random();
        for(int ball=0; ball<totNoOfBalls; ball++){
            getUserInput(scanner);
            int event = -1;
            while(event<player.getSkillLevel()){
                int eventId = rand.nextInt(TOTAL_PLAY_EVENTS);
                if(0<=eventId && eventId<=2){event = 0;}
                else if(3<=eventId && eventId<=5){event = 1;}
                else if(6<=eventId && eventId<=8){event = 2;}
                else if(9<=eventId && eventId<=11){event = 3;}
                else if(12<=eventId && eventId<=13){event = 4;}
                else if(14<=eventId && eventId<=15){event = 6;}
                else if(eventId==16){event = 7;}
                else if(eventId==17){event = 8;}
            }
            if(0<=event && event<=6){
                System.out.println(event+" runs scored by player "+(Integer.parseInt(player.getName())+1));
                player.setScore(event);
                battingTeam.setTotScore(event);
            }
            else if(event==7){
                System.out.println("Caught out. Player "+(Integer.parseInt(player.getName())+1)+" is out!");
                player.setWicketType("Caught out");
                battingTeam.setTotWickets(1);
                player = battingTeam.getNewPlayer();
            }
            else if(event==8){
                System.out.println("Bowled out. Player "+(Integer.parseInt(player.getName())+1)+" is out!");
                player.setWicketType("Bowled out");
                battingTeam.setTotWickets(1);
                player = battingTeam.getNewPlayer();
            }
            String overs = "("+((ball+1)/3)+"/"+((ball+1)%3)+")\n";
            if(ball%3==2 && ball!=0){
                System.out.println("End of over");
            }
            System.out.println("Score summary "+battingTeam.getTotScore()+"/"+battingTeam.getTotWickets()+" "+overs );
            if(battingTeam.getTotScore()>targetScore && targetScore>-1){
                break;
            }
            if(player==null){
                break;
            }
        }
        return battingTeam.getTotScore();
    }
    public void scoreBoard(List<Team> teams){
        System.out.println("-----------------------------------\nScore Board:\n");
        for (int i = 0; i< NO_OF_TEAMS; i++){
            System.out.println("Team "+teams.get(i).getName()+" Total Score: "+teams.get(i).getTotScore()+"/"
                    +teams.get(i).getTotWickets());
            List<Player> players = teams.get(i).getPlayerList();
            System.out.println("\nPlayer     Score      Wicket Type\n---------------------------------");

            for (int j=0; j< NO_OF_PLAYERS;j++){
                System.out.println("Player "+(Integer.parseInt(players.get(j).getName())+1)+"     "+players.get(j).getScore()+
                        "         "+players.get(j).getWicketType()+"\n---------------------------------\n");
            }
        }
    }
}
