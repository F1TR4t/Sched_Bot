package DiScribe;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

public class Bot {

    public static void main(String[] args) {
        GatewayDiscordClient client = DiscordClientBuilder.create("NzYzMTA4MzI0MjU3NjkzNzA2.X3y6Ag.vmb8F1wds6zAEBIbaIHRSmwgeHo").build().login().block();
        
        client.getEventDispatcher().on(ReadyEvent.class).subscribe(event -> {
          User self = event.getSelf();
          System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
        });
        
        // ---------------------- START OF EDITABLE SECTION ---------------------- //
        
        client.getEventDispatcher().on(MessageCreateEvent.class)
        .map(MessageCreateEvent::getMessage)
        .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
        .filter(message -> message.getContent().equalsIgnoreCase("!ping"))
        .flatMap(Message::getChannel)
        .flatMap(channel -> channel.createMessage("Pong!"))
        .subscribe();
        
        // ---------------------- END OF EDITABLE SECTION ---------------------- //
        
        client.onDisconnect().block();
    }

}