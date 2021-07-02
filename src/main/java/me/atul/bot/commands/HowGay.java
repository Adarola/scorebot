package me.atul.bot.commands;
import java.util.Random;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
//import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HowGay extends Command {

    public HowGay(){
        this.name = "howgay";
        this.help = "Experience RNG predicting the level of Gay you are.";
    }

    protected void execute(CommandEvent e){

        Random rand = new Random();
        String[] message = e.getMessage().getContentRaw().split(" ");



        String[] gayScale = {"is kinda gay",
                             "is hella gay",
                             "is mega gay",
                             "is not very gay",
                             "is not gay at all",
                             "is completely gay",
                             "is slighly gay"};

        //TODO: Need to get just the authors username, not their ID
        if(message.length > 1) {
            if(message.length > 2){
                String subject = "";
                for(int i = 1; i < message.length; i++){
                    subject += message[i] + " ";
                }
                e.getChannel().sendMessage(subject + gayScale[rand.nextInt(6)]).queue();
                return;
            }
                e.getChannel().sendMessage(message[1] + " " + gayScale[rand.nextInt(6)]).queue();

        } else {
            String name = e.getAuthor().getName();
            e.getChannel().sendMessage(name + " " + gayScale[rand.nextInt(6)]).queue();

        }
    }
}
