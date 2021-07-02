package me.atul.bot.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Leaderboard extends Command {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fileName = "C:\\Users\\lutal\\temp\\data.json";
    private final TypeReference<HashMap<String,PassPoints>> typeRef = new TypeReference<HashMap<String, PassPoints>>() {
    };
    private final File jsonFile;
    private Map<String, PassPoints> points;

    public Leaderboard(){
        this.name = "leaderboard";
        this.help = "Ranks every user in the server based on points and passes.";
        this.jsonFile = new File(fileName);
        init();

    }

    @SneakyThrows
    private void init(){
        if (jsonFile.exists()) {
            points = objectMapper.readValue(this.jsonFile, typeRef);
        } else {
            points = new HashMap<>();
        }

    }

    protected void execute(CommandEvent e) {
        EmbedBuilder embed = new EmbedBuilder();

        String[] members = new String[e.getGuild().getMemberCount()];

        try{

            // Setup for sorting
            for(int i = 0; i < members.length; i++){
                String name = e.getGuild().getMembers().get(i).getEffectiveName();
                members[i] = name;
                points.putIfAbsent(name, new PassPoints());
            }

            // Sorts array through insertion sort
            for(int i = 1; i < members.length; i++){
                String keyStr = members[i];
                int key = points.get(members[i]).getPoints();
                int j = i - 1;

                while(j >= 0 && points.get(members[j]).getPoints() > key){
                    members[j + 1] = members[j];
                    j--;
                }
                members[j + 1] = keyStr;
            }

            embed.setTitle("Gay Rankings");

            for(int i = members.length - 1; i >= 0; i--){
                embed.addField( members[i], "\n Points: " + points.get(members[i]).getPoints() + "\n Passes: " + points.get(members[i]).getPass() , false);
            }
            objectMapper.writeValue(this.jsonFile, points);

        } catch(IOException ioException){
            ioException.printStackTrace();
        }

        e.reply(embed.build());
    }


}
