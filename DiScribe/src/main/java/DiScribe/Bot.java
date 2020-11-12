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
	private static int numEvents = 0;
	private static String[] cmds = {"create", "remove", "edit", "list", "display"};
	private static String[] attr = {"name", "desc", "active", "date", "duration", "server", "channel", "recur", "frequency", "max_frequency", "number_of_announcements"};
	
	public static void process(String[] command, int len, MessageCreateEvent event) {
		
		// Make sure the command is not empty
		if ( len <= 0 ) { return; }
		
		// Make sure the command has Keyword
		if ( command[0] == null ) { return; } 
		
		if ( command[0].equals(cmds[0])) {
			if ( (command.length == 2) && events.add(new Event(command[1])) ) { // If user specifies a name
				numEvents++;
				event.getMessage()
				.getChannel().block()
				.createMessage("Created the event \"" + command[1] + "\"").block();
			} else if (command.length == 1 && events.add(new Event("Event" + numEvents))) { // When user does not specify a name
				event.getMessage()
				.getChannel().block()
				.createMessage("Created the event \"Event" + numEvents++ + "\"").block();
			} else { // When they add too many arguments
				event.getMessage()
				.getChannel().block()
				.createMessage("The create command requires you to name the event\n"
						+ "Note: the name should be less than 50 characters.\n\n"
						+ "```create [event_name]```\n\n"
						+ "Example: Creating an event BBQ\n"
						+ "```create BBQ```").block();
			}
		}
		
		if ( command[0].equals(cmds[1])) {
			if ( (command.length>1) && events.remove(new Event(command[1])) ) { // Specifies event to be removed
				event.getMessage()
				.getChannel().block()
				.createMessage("Removed the event \"" + command[1] + "\"").block();
			} else { // No Specification whatsoever
					event.getMessage()
					.getChannel().block()
					.createMessage("The remove command requires you to name the event you want to remove\n\n"
							+ "```remove [event_name]```\n\n"
							+ "Example: If I created an event BBQ\n"
							+ "```remove BBQ```").block();
				}
		}
		
		/*if ( command[0].equals(cmds[3])) {
			event.getMessage()
			.getChannel().block()
			.createMessage(events.displayList()).block();
		}*/
		
		if ( command[0].equals(cmds[4])) { // Prone to Null Pointer Exceptions
			if ( command.length == 2) {
				event.getMessage()
				.getChannel().block()
				.createMessage("Displaying the event \"" + command[1] +"\"\n\n"
					+ events.get(events.find(new Event(command[1]))).toString() ).block();
			} else {
				event.getMessage()
				.getChannel().block()
				.createMessage("The display command requires you to name the event you want to remove\n\n"
						+ "```display [event_name]```\n\n"
						+ "Example: If I created an event BBQ\n"
						+ "```display BBQ```").block();
			}
		}
		
		if ( command[0].equals(cmds[2])) { // To be worked on
			if ( command.length == 4 ) {
				
			}
			else {
			event.getMessage()
			.getChannel().block()
			.createMessage("The edit command requires you to name the event you want to edit and the attributes as well\n\n"
					+ "```edit [event_name] [attribute] [desired_value]```\n\n"
					+ "Example, if I want to change the event name of BBQ to Ketchup\n"
					+ "```edit BBQ name Ketchup```").block();
			}
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