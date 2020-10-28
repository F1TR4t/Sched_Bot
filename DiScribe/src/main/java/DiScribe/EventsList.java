package DiScribe;

import java.util.ArrayList;

public class EventsList {
	
	private ArrayList<Event> list;
	
	public EventsList() {
		list = new ArrayList<Event>();
		
	}
	
	public boolean add(Event E)
	{
		if(E == null)
		{
			return false;
		}
		
		return list.add(E);
	}
	
	public boolean remove(Event E)
	{
		if(E == null)
		{
			return false;
		}
		
		// Check to see if the Event is stored in the list
		
		return list.remove(E);
	}
	
	public Event get(Event E)
	{
		return list.get(list.indexOf(E));
	}
	
	public static void main(String []args)
	{
		Event bob = new Event("bob");
		EventsList list = new EventsList();
		
		list.add(bob);
		System.out.println(list.get(bob)); // Works
		
		System.out.println(list.remove(bob));
		System.out.println(list.remove(bob)); // Works
	}
	
}
