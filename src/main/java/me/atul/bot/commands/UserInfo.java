package me.atul.bot.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserInfo extends Command {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fileName = "C:\\Users\\lutal\\temp\\data.json";
    private final TypeReference<HashMap<String,PassPoints>> typeRef = new TypeReference<HashMap<String, PassPoints>>() {
    };
    private final File jsonFile;

    public UserInfo(){
        this.name = "info";
        this.help = "Displays your stats. (# of points and # of passes)";
        this.jsonFile = new File(fileName);
    }

    protected void execute(CommandEvent e){
        EmbedBuilder embed = new EmbedBuilder();
        Map<String, PassPoints> points;

        try {
            if (jsonFile.exists()) {
                points = objectMapper.readValue(this.jsonFile, typeRef);
            } else {
                points = new HashMap();
            }

            String[] message = e.getMessage().getContentRaw().split(",");
            if (e.getMessage().getMentionedMembers().isEmpty() || message.length == 0) {
                e.reply("Please enter a valid username");
                return;
            }
            Member name = e.getMessage().getMentionedMembers().get(0);
            if (points.containsKey(name.getEffectiveName())) {
                embed.setTitle("User Info");
                embed.setThumbnail(name.getUser().getAvatarUrl());
                embed.addField("Name:", name.getEffectiveName(), false);
                embed.addField("Gay Points:", points.get(name.getEffectiveName()).getPoints() + " points", true);
                embed.addField("Gay Passes:", points.get(name.getEffectiveName()).getPass() + " passes", true);
                embed.setColor(Color.YELLOW);
                e.reply(embed.build());
                return;
            } else {
                points.putIfAbsent(name.getEffectiveName(), new PassPoints());
                embed.setTitle("User Info");
                embed.setThumbnail(name.getUser().getAvatarUrl());
                embed.addField("Name:", name.getEffectiveName(), false);
                embed.addField("Gay Points:", points.get(name.getEffectiveName()).getPoints() + " points", true);
                embed.addField("Gay Passes:", points.get(name.getEffectiveName()).getPass() + " passes", true);
                embed.setColor(Color.YELLOW);
                e.reply("Test: " + points.get(name.getEffectiveName()).getPoints());

            }

            objectMapper.writeValue(this.jsonFile, points);

        } catch(IOException ioException){
            ioException.printStackTrace();

        }
    }
}
