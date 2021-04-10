package com.spark.cricketmod.models;

import java.util.Random;

public class Player {
    private String name;
    private int score;
    private String wicketType;
    private int skillLevel; //o to 2 for easy, 0 to 4 for medium, 2 to 4 for hard (possible values: 0,1,2,3,4)

    public Player(String name,String difficultLevel){
        this.name=name;
        this.score=0;
        this.wicketType="--";
        this.skillLevel = setSkillLevel(difficultLevel); //set skill level of player according to the difficulty level
    }

    private int setSkillLevel(String difficultLevel) {
        Random rand = new Random();
        int skill=0;
        if(difficultLevel.equals("easy")){
            skill = rand.nextInt(2);
        }
        else if(difficultLevel.equals("medium")){
            while(skill==0){
                skill = rand.nextInt(4);
            }
        }
        else if (difficultLevel.equals("hard")){
            while(skill==0||skill==1){
                skill = rand.nextInt(4);
            }
        }
        return skill;
    }

    public String getName(){return name;}

    public void setScore(int result){this.score+=result;}
    public int getScore(){return score;}

    public String getWicketType(){return wicketType;}
    public void setWicketType(String wicketType) {this.wicketType = wicketType;}

    public int getSkillLevel(){return skillLevel;}

}
