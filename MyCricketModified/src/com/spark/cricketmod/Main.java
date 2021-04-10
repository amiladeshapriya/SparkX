package com.spark.cricketmod;

import com.spark.cricketmod.controller.GameController;
import com.spark.cricketmod.controller.gamemods.FiveOverGame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Spark Cricket!\nEnter 'p' to start the game. Enter 'e' to exit.\n");

        try(Scanner scan = new Scanner(System.in)){
            while(scan.hasNext()){
                String userIn = getUserInput1(scan);
                if(userIn.equalsIgnoreCase("e")){
                    break;
                }
                System.out.println("Select Game Difficulty Level\nEnter 'e' for Easy.\nEnter 'm' for Medium.\nEnter 'h' for Hard.\n");
                userIn = getUserInput2(scan);
                if(userIn.equalsIgnoreCase("e")){
                    System.out.println("Easy Mode Selected");
                    GameController game = new FiveOverGame();
                    game.playGame("easy");
                }
                else if(userIn.equalsIgnoreCase("m")){
                    System.out.println("Medium Mode Selected");
                    GameController game = new FiveOverGame();
                    game.playGame("medium");
                }
                else if(userIn.equalsIgnoreCase("h")){
                    System.out.println("Hard Mode Selected");
                    GameController game = new FiveOverGame();
                    game.playGame("hard");
                }

            }

        }
        catch(Exception e){
            System.out.println("Input error! Please check your input."+e);
        }
    }

    private static String getUserInput2(Scanner scan){
        String userInput = scan.next();
        while(userInput==null||!(userInput.equalsIgnoreCase("e")||userInput.equalsIgnoreCase("m")||userInput.equalsIgnoreCase("h"))){
            System.out.println("Invalid input! Please check input and try again!");
            userInput=scan.next();
        }
        return userInput;
    }

    public static String getUserInput1(Scanner scan){
        String userInput = scan.next();
        while(userInput==null||!(userInput.equalsIgnoreCase("p")||userInput.equalsIgnoreCase("e"))){
            System.out.println("Invalid input! Please check input and try again!");
            userInput=scan.next();
        }
        return userInput;
    }
}
