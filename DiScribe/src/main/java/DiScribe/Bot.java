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
		
		if ( command[0].equals("create")) {
			if ( (command.length>1) && events.add(new Event(command[1])) ) {
				event.getMessage()
				.getChannel().block()
				.createMessage("Created the event \"" + command[1] + "\"").block();
			} else {
				event.getMessage()
				.getChannel().block()
				.createMessage("The create function requires you to name the event\n"
						+ "Note: the name should be less than 50 characters.\n\n"
						+ "```create [event_name]```\n\n"
						+ "Example: Creating an event BBQ\n"
						+ "```create BBQ```").block();
			}
		}
		
		if ( command[0].equals("remove")) {
			if ( (command.length>1) && events.remove(new Event(command[1])) ) {
				event.getMessage()
				.getChannel().block()
				.createMessage("Removed the event \"" + command[1] + "\"").block();
			} else {
					event.getMessage()
					.getChannel().block()
					.createMessage("The remove function requires you to name the event you want to remove\n\n"
							+ "```remove [event_name]```\n\n"
							+ "Example: If I created an event BBQ\n"
							+ "```remove BBQ```").block();
				}
		}
		
		if ( command[0].equals("list")) {
			event.getMessage()
			.getChannel().block()
			.createMessage(events.displayList()).block();
		}
		
		if ( command[0].equals("display")) {
			event.getMessage()
			.getChannel().block()
			.createMessage("Displaying the event \"" + command[1] +"\"\n\n"
					+ events.get(events.find(new Event(command[1]))).toString() ).block();
		}
		
		if ( command[0].equals("edit")) { // To be worked on
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