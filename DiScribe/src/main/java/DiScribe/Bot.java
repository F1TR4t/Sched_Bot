package DiScribe;



import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Mono;

public class Bot {
	
	private static EventsList events;
	
	public static void process(String[] command, int len, MessageCreateEvent event) {
		
		// Make sure the command is not empty
		if ( len <= 0 ) { return; }
		
		// Make sure the command has Keyword
		if ( command[0] == null ) { return; } 
		
		if ( command[0].equals("ping")) {
			event.getMessage()
	        .getChannel().block()
	        .createMessage("Pong!").block();
		}
		
		if ( command[0].equals("create")) {
			event.getMessage()
			.getChannel().block()
			.createMessage("Creating " + command[1]).block();
		}
		
		if ( command[0].equals("remove")) {
			event.getMessage()
			.getChannel().block()
			.createMessage("Removing " + command[1]).block();
		}
		
		if ( command[0].equals("list")) {
			event.getMessage()
			.getChannel().block()
			.createMessage("Listing Events").block();
		}
		
		if ( command[0].equals("display")) {
			event.getMessage()
			.getChannel().block()
			.createMessage("Displaying " + command[1]).block();
		}
		
		if ( command[0].equals("edit")) {
			event.getMessage()
			.getChannel().block()
			.createMessage("Editing " + command[1]).block();
		}
	}

    public static void main(String[] args) {
        GatewayDiscordClient client = DiscordClientBuilder.create("NzYzMTA4MzI0MjU3NjkzNzA2.X3y6Ag.vmb8F1wds6zAEBIbaIHRSmwgeHo").build().login().block();
        
        client.getEventDispatcher().on(ReadyEvent.class).subscribe(event -> {
          User self = event.getSelf();
          System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
        });
        
        events = new EventsList();
        
        // ---------------------- START OF EDITABLE SECTION ---------------------- //
       
        
        client.getEventDispatcher().on(MessageCreateEvent.class)
        .subscribe(event -> {
            final String content = event.getMessage().getContent();
            final String[] command = content.split(" ");
            final Mono<MessageChannel> channel = event.getMessage().getChannel();
            process(command, command.length, event);
        });
        
        // ---------------------- END OF EDITABLE SECTION ---------------------- //
        
        client.onDisconnect().block();
    }

}