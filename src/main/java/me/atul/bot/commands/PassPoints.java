package me.atul.bot.commands;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassPoints {

    private int points;
    private int pass;

    public PassPoints(int points){
        this.points = points;
        this.pass = 0;
    }

    public void addPoints(){
        points += 5;
    }

    public int getPoints(){ return points; }

    public void addPass(){ pass++; };



}

