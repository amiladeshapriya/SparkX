package com.spark.cricketmod.controller;

import com.spark.cricketmod.controller.gamemods.FiveOverGame;
import com.spark.cricketmod.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class GameController {


    public static final int NO_OF_PLAYERS = 6;

    abstract protected int bat(Team battingTeam,int targetScore, Scanner scanner); //returns total score of the team at the end of inning

    public void playGame(String difficultyLevel){
        try(Scanner scanner = new Scanner(System.in)){
            List<Team> teams = new ArrayList<>();
            createTeams(teams,scanner,difficultyLevel); //creates teams with players according to the difficulty level
            Team wonTossTeam = getWonTossTeam(teams); //check toss result
            Team lossTossTeam = teams.get(0).getWonTheToss() ? teams.get(1):teams.get(0);
            int targetScore = bat(wonTossTeam,-1,scanner); //first inning score
            FiveOverGame scoreObj = new FiveOverGame();
            int secondScore=bat(lossTossTeam, targetScore,scanner);  //second inning score

            if(teams.get(0).getTotScore()>teams.get(1).getTotScore()){
                System.out.println("Your team "+teams.get(0).getName()+" has won the match by "+Math.abs(targetScore-secondScore)+" runs");
            }else if(teams.get(0).getTotScore()<teams.get(1).getTotScore()){
                System.out.println("Computer's team "+teams.get(1).getName()+" has won the match by "+Math.abs(targetScore-secondScore)+" runs");
            }else {
                System.out.println("Match is drawn!");
            }
            scoreObj.scoreBoard(teams); //shows score board at the end of the match

        }
        catch (Exception e){
            System.out.println("Input error! Please check your input.");
        }
        System.out.println("Game Over");

    }

    private Team getWonTossTeam(List<Team> teams) {
        Random rand = new Random();
        Team wonTossTeam = rand.nextBoolean() ? teams.get(0):teams.get(1);
        wonTossTeam.setWonTheToss(true);
        if(teams.get(0).getWonTheToss()){
            System.out.println("Your Team: "+wonTossTeam.getName()+" won the toss!");
        }
        else{
            System.out.println("Computer's Team: "+wonTossTeam.getName()+" won the toss!");
        }
        return wonTossTeam;
    }

    private void createTeams(List<Team> teams,Scanner scanner,String difficultyLevel) {
        String firstTeam;
        System.out.println("Enter your team name: ");
        String input = scanner.nextLine();
        while (input == null||input.isEmpty()){
            System.out.println("Invalid name! Please try again!\nEnter your team name: ");
            input = scanner.nextLine();
        }
        firstTeam = input;
        Team YourTeam = new Team(input, NO_OF_PLAYERS,difficultyLevel);
        teams.add(YourTeam);

        System.out.println("Enter computer's team name: ");
        input = scanner.nextLine();
        while (input == null||input.isEmpty()||input.equals(firstTeam)){
            if(input.equals(firstTeam)){System.out.println("Name is already given!\nEnter computer's team name: ");}
            else{System.out.println("Invalid name! Please try again!\nEnter computer's team name: ");}
            input = scanner.nextLine();
        }
        Team ComputersTeam = new Team(input, NO_OF_PLAYERS,difficultyLevel);
        teams.add(ComputersTeam);
    }
    public void getUserInput(Scanner scanner){
        System.out.println("Enter 'p' to play!");
        String userInput = scanner.next();
        while (userInput == null||!userInput.equalsIgnoreCase("p")){
            System.out.println("Invalid input! Please check and try again!.");
            userInput = scanner.nextLine();
        }
    }
}
