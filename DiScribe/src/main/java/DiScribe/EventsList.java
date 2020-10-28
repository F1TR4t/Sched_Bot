package DiScribe;

import java.util.ArrayList;

public class EventsList {
	
	private ArrayList<Event> list;
	
	public EventsList() {
		list = new ArrayList<Event>();
		
	}
	
	public boolean addEvent(Event newEvent)
	{
		if(newEvent == null)
		{
			return false;
		}
		
		list.add(newEvent);
		return true;
	}
	
	public boolean removeEvent(Event eventID)
	{
		if(eventID == null)
		{
			return false;
		}
		
		list.remove(eventID);
	}
	
	public Event getEvent(Event eventID)
	{
		return list.get(list.indexOf(eventID));
	}
	
	public static void main(String []args)
	{
		Event bob = new Event("bob");
		EventsList list = new EventsList();
		
		list.addEvent(bob);
		System.out.print(list.getEvent(bob));
	}
	
}
