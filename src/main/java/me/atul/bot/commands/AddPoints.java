package me.atul.bot.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddPoints extends Command {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String fileName = "C:\\Users\\lutal\\temp\\data.json";
    private final TypeReference<HashMap<String,PassPoints>> typeRef = new TypeReference<HashMap<String, PassPoints>>() {
    };

    private final File jsonFile;
    public AddPoints(){
        this.name = "addpoints";
        this.help = "Adds points to the specified user. Phrase: +addpoints @User";
        this.jsonFile = new File(fileName);

    }


    protected void execute(CommandEvent e) {
        Map<String, PassPoints> points;

        try {

            if (jsonFile.exists()) {
                points = objectMapper.readValue(this.jsonFile, typeRef);
            } else {
                points = new HashMap<>();
            }
            String[] message = e.getMessage().getContentRaw().split(" ");
            if (e.getMessage().getMentionedMembers().isEmpty() || message.length == 0) {
                e.reply("Please enter a valid username");
                return;
            }
            Member name = e.getMessage().getMentionedMembers().get(0);
            if (points.containsKey(name.getEffectiveName())) {
                points.get(name.getEffectiveName()).addPoints();
            } else {
                points.putIfAbsent(name.getEffectiveName(), new PassPoints(5));
            }

            e.reply("Success! " + name.getAsMention() + " now has " + points.get(name.getEffectiveName()).getPoints() + " gay points.");
            objectMapper.writeValue(this.jsonFile, points);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
