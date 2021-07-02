package me.atul.bot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GayPride extends Command {

    public GayPride(){
        this.name = "gaypride";
        this.help = "Celebrate Gay Pride with this awesome flag!";
    }

    protected void execute(CommandEvent e){

        String message = e.getMessage().getContentRaw();

        e.getChannel().sendMessage("https://media.discordapp.net/attachments/519741177864519690/822563032654807050/Gay_Pride_Flag_-_Animated.gif").queue();
    }

}
