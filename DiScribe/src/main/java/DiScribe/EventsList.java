package DiScribe;

import java.util.ArrayList;
import java.util.*;

public class EventsList {
	
	private ArrayList<Event> list;
	private StringBuilder display;
	
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
	
	public boolean remove(String name) {
		if(name == null) {
			return false;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName() == name) {
				return list.remove(list.get(i));
			}
		}
		return false;
	}
	
	public boolean contains(String name) {
		if(name == null) {
			return false;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName() == name) {
				return true;
			}
		}
		return false;
	}
	
	public Event get(String name) {
		if(name == null) {
			return null;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName() == name) {
				return list.get(i);
			}
		}
		return null;
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
		int stringLen = 130;
		display = new StringBuilder(stringLen);
		display.append("                                                                                                                               ");
		System.out.println(" Name                                                       Date                                                       Active ");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < list.size(); i++) {
			display.insert(1, list.get(i).getName());
			display.insert(60, list.get(i).getDate());
			display.insert(120, list.get(i).getActive());
			System.out.println(display);
			display = new StringBuilder(stringLen);
			display.append("                                                                                                                               ");
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
		bob.setDate("2000-01-01");
		jim.setDate("2001-02-01");
		tim.setDate("2002-03-01");
		joe.setDate("2003-04-01");
		sam.setDate("2004-05-01");
		jay.setDate("2005-06-01");
		jen.setDate("2006-07-01");
		bob.setActive(true);
		jim.setActive(true);
		tim.setActive(true);
		joe.setActive(true);
		sam.setActive(true);
		jay.setActive(false);
		jen.setActive(false);
		
		
		
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
		
		System.out.println(list.remove("bob")); // Works
		
		list.displayList();
		
	}
	
}
