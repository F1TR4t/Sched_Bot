package DiScribe;

import java.util.ArrayList;

public class EventsList {
	
	private ArrayList<Event> list;
	private ArrayList<String> nameList;
	
	public EventsList() {
		list = new ArrayList<Event>();
		nameList = new ArrayList<String>();
	}
	

	public boolean add(Event E) {
		if(E == null) {
			return false;
		}
		nameList.add(E.toString());
		return list.add(E);
	}
	
	public boolean add(int index, Event E) {
		if(E == null) {
			return false;
		}
		nameList.add(index, E.toString());
		list.add(index, E);
		return true;
	}
	
	public boolean remove(Event E) {
		if(E == null) {
			return false;
		}
		else if(list.contains(E) == false) {
			return false;
		}
		nameList.remove(E.toString());
		return list.remove(E);
	}
	
	public boolean contains(Event E) {
		return list.contains(E);
	}
	
	public Event get(int index) {
		if(index < 0 || index > list.size()) {
			return null;  
		}
		else if(list.size() == 0) {
			return null;
		}
		
		return list.get(index);
	}
	
	public int length() {
		return list.size();
	}
	
	public int find(Event E) {
		if(E == null) {
			return -1;
		}
		else if(list.size() == 0) {
			return -1;
		}
		
		return list.indexOf(E);
	}
	
	public ArrayList<Event> ascAlphArray() {
		return list;
	}
	
	public ArrayList<Event> desAlphARray() {
		return list;
	}
	
	public ArrayList<Event> ascDateArray() {
		return list;
	}
	
	public ArrayList<Event> desDateArray() {
		return list;
	}
	
	public String displayList() {
		// Displays list in string format
		return "";
	}
	
	public static void main(String []args) {
		Event bob = new Event("bob");
		EventsList list = new EventsList();
		
		list.add(bob);

		System.out.println(list.get(0)); // Works
		
		System.out.println(list.remove(bob));
		System.out.println(list.remove(bob)); // Works

	}
	
}
