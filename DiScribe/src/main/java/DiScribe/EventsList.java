package DiScribe;

import java.util.ArrayList;

public class EventsList {
	
	private ArrayList<Event> list;
	private ArrayList<String> nameList;
	
	public EventsList() {
		list = new ArrayList<Event>();
	}
	

	public boolean add(Event E) {
		if(E == null) {
			return false;
		}
		return list.add(E);
	}
	
	public boolean add(int index, Event E) {
		if(E == null) {
			return false;
		}
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
		return list.remove(E);
	}
	
	public boolean contains(Event E) {
		if(list.contains(E)) {
			return true;
		}
		return false;
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
	
	public void displayList() {
		// Temporary display method without date or status
		
		System.out.println(" Name                                        Date                                        Active");
		System.out.println("------------------------------------------------------------------------------------------------");
		for(int i = 0; i < list.size(); i++) {
			System.out.printf("%s \n", list.get(i).toString());
		}
		System.out.print("\n");
	}
	
	public static void main(String []args) {
		EventsList list = new EventsList();
		
		Event bob = new Event("bob");
		Event jim = new Event("jim");
		Event tim = new Event("tim");
		Event joe = new Event("joe");
		Event sam = new Event("sam");
		Event jay = new Event("jay");
		Event jen = new Event("jen");
		
		// Testing regular add()
		list.add(bob);
		list.add(jim);
		list.add(tim);
		list.add(joe);
		list.add(sam);
		list.add(jay);
		
		// Testing displayList()
		list.displayList(); // Works
		
		list.add(3, jen);
		System.out.println("Testing adding at an index");
		list.displayList();
		
		System.out.println(list.remove(bob));
		System.out.println(list.remove(bob)); // Works
		
		list.displayList();
		
	}
	
}
