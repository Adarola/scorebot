package me.atul.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Titles extends Command {

    public Titles(){
        this.name = "titles";
        this.help = "displays all titles and their point values";
    }

    protected void execute(CommandEvent e){

        e.reply("Titles:\n" +
                "\n" +
                "15 points: GAY\n" +
                "25 points: BIG GAY\n" +
                "35 points: MEGA GAY\n" +
                "45 points: ULTRA GAY\n" +
                "55 points: PLANETARY GAY\n" +
                "65 points: GALAXY GAY\n" +
                "75 points: UNIVERSALLY GAY\n" +
                "85 points: HEAVENLY GAY\n" +
                "95 points: GODLY GAY\n" +
                "\n" +
                "(Point values and names subject to change.)");
    }
}
