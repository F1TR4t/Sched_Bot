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
	private static String[] cmds = {"help", "create", "remove", "edit", "list", "display"};
	private static String[] attr = {"name", "desc", "active", "date", "duration", "server", "channel"};
	
	private static String  help = "List of Commands include:\n-------------------------\n"
			+ "create [Name of Event]\ndelete [Name of Event]\nedit   [Name of Event]\n"
			+ "display [Name of Event]\nlist\n\nIf you would like to know how a command works, write the command name then \"?\" after it.";
	
	private static String empty = "For a list of commands, use the \"help\" command.";

	public static void process(String[] cmd, int len, MessageCreateEvent event) {
		
		// Might not be necessary, but in case of empty Strings
		if (len == 0) {
			event.getMessage()
				.getChannel().block()
				.createMessage(empty).block();
		} 
		
		// For the help command
		if (cmd[0].toLowerCase().equals(cmds[0])) {
			event.getMessage()
				.getChannel().block()
				.createMessage(help).block();
		}
		
		// for the Create Command
		if (cmd[0].toLowerCase().equals(cmds[1])) {
			
			if ( len == 1 ) { 						// If they don't provide an Event Name
				String genName = "Event#" + numEvents;
				if ( events.add(new Event(genName)) ) { 		// Successful creation
					event.getMessage()
						.getChannel().block()
						.createMessage("Created an event named \"Event#" + numEvents++ + "\"").block();
				} else {										// Failure in creation
					event.getMessage()
					.getChannel().block()
					.createMessage("Something went wrong when creating the event named \"Event#" + numEvents + "\"").block();
				}
			}
			
			if ( len > 1 ) { 						// If they do provide an event name
				
				if ( cmd[1].equals("?") ) { 			// Want to know more about the create method
					event.getMessage()
						.getChannel().block()
						.createMessage("The \"create\" command is meant for creating events. It can either create a generic event if a name isn't given, or an event with a name provided by the user. Please see example below\n\n"
							+ "```create\nCreated an event named \"Event#1\"\ncreate BBQ\nCreated an event named \"BBQ\"```\n\n").block();
				} else { 								// They do have a name
					String name = cmd[1];
					for ( int i = 2; i < len; i++ ) {
						name += " ";
						name += cmd[i];
					}
					if ( events.add(new Event(name)) ) { 		// Successful creation
						event.getMessage()
							.getChannel().block()
							.createMessage("Created an event named \"" + name + "\"").block();
					} else { 									// Failure in creation
							event.getMessage()
						.getChannel().block()
						.createMessage("Something went wrong when creating the event named \"" + name + "\"").block();
					}
				}
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