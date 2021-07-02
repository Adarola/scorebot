package me.atul.bot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.atul.bot.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.EnumSet;

public class Bot extends ListenerAdapter {

    public static void main(String[] args) throws Exception{

        JDA jda = JDABuilder.createDefault("ODIyMDIzMjkwMjE3NDMxMTAx.YFMO2A.kjanaxMOSzp-PQItQqSvQHxZYjs", EnumSet.allOf(GatewayIntent.class)).setMemberCachePolicy(MemberCachePolicy.ALL).build();


        CommandClientBuilder builder = new CommandClientBuilder();

        // Setting up the builder
        builder.setPrefix("+");
        builder.setHelpWord("help");
        builder.setOwnerId("822023290217431101");
        builder.setActivity(Activity.listening("+help"));

        // Setting up the commands
        builder.addCommand(new HowGay());
        builder.addCommand(new GayPride());
        builder.addCommand(new AddPoints());
        builder.addCommand(new AddPass());
        builder.addCommand(new UserInfo());
        builder.addCommand(new Leaderboard());
        builder.addCommand(new Titles());


        CommandClient client = builder.build();

        jda.addEventListener(client);
    }
}
