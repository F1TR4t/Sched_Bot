package DiScribe;

import java.util.ArrayList;

public class EventsList {
	
	private ArrayList<Event> list;
	
	public EventsList() {
		list = new ArrayList<Event>();
		
	}
	
	public boolean add(Event newEvent)
	{
		if(newEvent == null)
		{
			return false;
		}
		
		list.add(newEvent);
		return true;
	}
	
	public boolean remove(Event eventName)
	{
		if(eventName == null)
		{
			return false;
		}
		
		list.remove(eventName);
		return true;
	}
	
	public Event get(Event eventName)
	{
		return list.get(list.indexOf(eventName));
	}
	
	public static void main(String []args)
	{
		Event bob = new Event("bob");
		EventsList list = new EventsList();
		
		list.add(bob);
		System.out.print(list.get(bob));
	}
	
}
