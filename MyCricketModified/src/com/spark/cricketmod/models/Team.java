package com.spark.cricketmod.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private boolean wonTheToss;//True: Bating first False: Bowling first
    private int totScore;
    private int totWickets;
    private List<Player> playerList;
    private int currentBatsman;

    public Team(String name,int noOfPlayers,String difficultyLevel){
        this.name=name;
        this.totScore=0;
        this.wonTheToss=false;
        this.totWickets=0;
        this.playerList = new ArrayList<>();
        setPlayers(noOfPlayers,difficultyLevel);
        this.currentBatsman=-1;
    }

    public void setPlayers(int noOfPlayers,String difficultyLevel) {
        for (int i=0; i<noOfPlayers; i++){
            Player player = new Player(String.valueOf(i),difficultyLevel);
            playerList.add(player);
        }
    }

    public String getName(){return name;}

    public boolean getWonTheToss(){return wonTheToss;}
    public void setWonTheToss(boolean toss){this.wonTheToss = toss;}

    public int getTotScore(){return totScore;}
    public void setTotScore(int runs){
        this.totScore += runs;
    }

    public int getTotWickets(){return totWickets;}
    public void setTotWickets(int wickets){
        this.totWickets += wickets;
    }

    public List getPlayerList(){return playerList;}

    public Player getNewPlayer(){
        this.currentBatsman++;
        if(currentBatsman>playerList.size()){
            return null;
        }
        Player nextPlayer = playerList.get(currentBatsman);
        return nextPlayer;
    }

}
