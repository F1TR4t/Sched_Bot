package DiScribe;



import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;

public class Bot {
	
	private static GatewayDiscordClient client;
	private static EventsList events;
	private static int numEvents = 0;
	private static String[] cmds = {"help", "create", "remove", "edit", "list", "display"};
	private static String[] attr = {"name", "desc", "active", "date", "duration"};
	private static String attrHelp = "\"name\" - Event Name\n\"desc\" - Event Description\n\"active\" - Is the Event Active? Must be True or False\n"
			+ "\"date\" - Date of Event. Format is YYYY-MM-DD (August 3, 2021 is now 2021-08-03)\n\"duration\" - Duration of Event (in minutes, so if 2 hours, just type 120)\n";
	
	public static void process(String[] cmd, int len, MessageCreateEvent event) {
		
		
		// For the help command
		if (cmd[0].toLowerCase().equals(cmds[0])) {
			event.getMessage()
				.getChannel().block()
				.createMessage("List of Commands include:\n-------------------------\n"
						+ "create [Name of Event]\ndelete [Name of Event]\nedit   [Name of Event]\n"
						+ "display [Name of Event]\nlist\n\nIf you would like to know how a command works, write the command name then \"?\" after it.").block();
		}
		
		// for the Create Command
		else if (cmd[0].toLowerCase().equals(cmds[1])) {
			
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
		
		else if (cmd[0].toLowerCase().equals(cmds[2])) {
			
			if ( len == 1 ) {					// If they do not provide an Event Name
				event.getMessage()
					.getChannel().block()
					.createMessage("Please provide an event to edit. Here is a list of them.\n" + events.displayList()).block();
			}
			
			if ( len > 1 ) {
				
				if (cmd[1].equals("?")) {		// If they want to know more about remove
						event.getMessage()
							.getChannel().block()
							.createMessage("The \"remove\" command will delete an event from the list if it exists. You must provide the name of the event you wish to remove.\n"
								+ "```remove BBQ```\nThe command should have removed an event BBQ.").block();
				} else {						// If they provided a name
					String name = cmd[1];
					for ( int i = 2; i < len; i++ ) {		
						name += " ";
						name += cmd[i];
					}
					
					if ( events.remove(name) ) {			// Successful removal
						event.getMessage()
							.getChannel().block()
							.createMessage("The event \"" + name + "\" has been removed successfully.").block();
					} else {								// Failure in removal
						event.getMessage()
							.getChannel().block()
							.createMessage("The event \"" + name + "\" has not been removed successfully or may have not been found. Double check to see if it exists in the list.").block();
					}
				}
				
			}
		
		}
		
		
		else if (cmd[0].toLowerCase().equals(cmds[3])) {
			
			if ( len == 1 ) {					// If they do not provide an Event Name
				event.getMessage()
					.getChannel().block()
					.createMessage("Please provide an event to edit. Here is a list of them.\n\n" + events.displayList()).block();
			}
			
			if ( len > 1 ) {
				
				if (cmd[1].equals("?")) {		// If they want to know more about edit
					event.getMessage().getChannel().block()
					.createMessage("Here are a list of attributes you can edit:\n" + attrHelp +
							"\n\nMake sure to type the edit command as so:\n```edit [event name] , [attribute] [new value]```\n"
							+ "So if I did:\n```edit BBQ , name BeBeQ```\nI just changed the name of BBQ to BeBeQ.").block();
				} else {						// If they provided a name
					String evntName = cmd[1];
					int index = 0;
					for ( int i = 2; i < len; i++ ) {		
						if ( cmd[i].equals(",")) {
							index = i + 1;
							break;
						}
						evntName += " ";
						evntName += cmd[i];
					}
					
					if ( events.contains(evntName) ) {			// Successful finding Event to edit
						Event evnt = events.get(evntName);
						if ( cmd[index].toLowerCase().equals(attr[0])) {
							index++;
							String name = cmd[index];
							for ( int i = index + 1; i < len; i++ ) {
								name += " ";
								name += cmd[i];
							}
							evnt.setName(name);
						}
						
						else if ( cmd[index].toLowerCase().equals(attr[1])) {
							index++;
							String desc = cmd[index];
							for ( int i = index + 1; i < len; i++ ) {
								desc += " ";
								desc += cmd[i];
							}
							evnt.setDesc(desc);
						}
						
						else if ( cmd[index].toLowerCase().equals(attr[2])) {
							index++;
							boolean active = Boolean.parseBoolean(cmd[index]);
							evnt.setActive(active);
						}
						
						else if ( cmd[index].toLowerCase().equals(attr[3])) {
							index++;
							String date = cmd[index];
				            evnt.setDate(date);
						}
						
						else if ( cmd[index].toLowerCase().equals(attr[4])) {
							index++;
							int edt = Integer.parseInt(cmd[index]);
				            evnt.setEDT(edt);
						}
						
					} else {								// Failure in finding Event to edit
						event.getMessage()
							.getChannel().block()
							.createMessage("The event \"" + evntName + "\" has not been found. Double check to see if it exists in the list.").block();
					}
				}
				
			}
		}
		
		else if (cmd[0].toLowerCase().equals(cmds[4])) {
			
			if ( len > 1 ) {
				
				if (cmd[1].equals("?")) {		// If they want to know more about list
					events.ascAlphArray(); 		// By default, Alphabetically ascending
					event.getMessage()
						.getChannel().block()
						.createMessage("The \"list\" command displays all the events currently held in the Bot.").block();
				} 
				
			} else {							// Else, list the events
				event.getMessage()
					.getChannel().block()
					.createMessage(events.displayList()).block();
			}
			
		}
		
		else if (cmd[0].toLowerCase().equals(cmds[5])) {
			
			if ( len == 1 ) {					// If they do not provide an Event Name
				event.getMessage()
					.getChannel().block()
					.createMessage("Please provide an event to edit. Here is a list of them.\n" + events.displayList()).block();
			}
			
			if ( len > 1 ) {
				
				if (cmd[1].equals("?")) {		// If they want to know more about display
					event.getMessage()
						.getChannel().block()
						.createMessage("The \"display\" command will display the attributes of the Event provided.\n\n"
							+ "```display BBQ```\nShould display all the attributes regarding BBQ.").block();
				} else {						// If they provided a name
					String name = cmd[1];
					for ( int i = 2; i < len; i++ ) {		
						name += " ";
						name += cmd[i];
					}
					
					if ( events.contains(name) ) {			// Successful finding Event to display
						event.getMessage()
							.getChannel().block()
							.createMessage(events.get(name).toString()).block();
					} else {								// Failure in finding Event to display
						event.getMessage()
							.getChannel().block()
							.createMessage("The event \"" + name + "\" has not been found. Double check to see if it exists in the list.").block();
					}
				}
				
			}
		}
		
		
	}
	

    public static void main(String[] args) {
        client = DiscordClientBuilder.create("NzYzMTA4MzI0MjU3NjkzNzA2.X3y6Ag.Ea26RdHEbuLMWpMWC3cvOB8pY3I").build().login().block();
        
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
            process(command, command.length, event);
        });
        
        // ---------------------- END OF EDITABLE SECTION ---------------------- //
        
        client.onDisconnect().block();
    }

}