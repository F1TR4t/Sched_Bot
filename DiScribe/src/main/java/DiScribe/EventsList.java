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
		if(name == null || !contains(name)) {
			return false;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
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
			if(list.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public Event get(String name) {
		if(name == null || !contains(name)) {
			return null;
		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
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
		
		Collections.sort(list, Event.nameCompareAsc);
		
		return list;
	}
	
	public ArrayList<Event> desAlphArray() {
		
		Collections.sort(list, Event.nameCompareDes);
			
		return list;
	}
	
	public ArrayList<Event> ascDateArray() {
		
		Collections.sort(list, Event.dateCompareAsc);
		
		return list;
	}
	
	public ArrayList<Event> desDateArray() {
		
		Collections.sort(list, Event.dateCompareDes);
		
		return list;
	}
	
	public String displayList() {
		// Temporary display method without date or status
		int stringLen = 130;
		String msg = "";
		display = new StringBuilder(stringLen);
		display.append("                                                                                                                               ");
		msg += (" Name                                                       Date                                                       Active \n");
		msg += ("------------------------------------------------------------------------------------------------------------------------------\n");
		for(int i = 0; i < list.size(); i++) {
			display.insert(1, list.get(i).getName());
			display.insert(60, list.get(i).getDate());
			display.insert(120, list.get(i).getActive());
			msg += display.toString();
			msg += "\n";
			display = new StringBuilder(stringLen);
			display.append("                                                                                                                               ");
		}
		return msg;
	}
	
	public static void main(String []args) {
		EventsList list = new EventsList();
		
		Event bob = new Event("bob");
		Event jim = new Event("jim");
		Event tim = new Event("tim");
		Event joe = new Event("joe");
		Event sam = new Event("sam");
		Event anne = new Event("anne");
		Event jen = new Event("jen");
		bob.setDate("2005-01-01");
		jim.setDate("2014-02-01");
		tim.setDate("2003-03-01");
		joe.setDate("2020-07-01");
		sam.setDate("2020-01-01");
		anne.setDate("2020-12-01");
		jen.setDate("2020-12-14");
		bob.setActive(true);
		jim.setActive(true);
		tim.setActive(true);
		joe.setActive(true);
		sam.setActive(true);
		anne.setActive(false);
		jen.setActive(false);
		
		
		
		// Testing regular add()
		list.add(bob);
		list.add(jim);
		list.add(tim);
		list.add(joe);
		list.add(sam);
		list.add(anne);
		
		// Testing displayList()
		list.displayList(); // Works
		
		list.add(3, jen);
		System.out.println("Testing adding at an index");
		System.out.println(list.displayList());
		
		System.out.println("Testing name sort Ascending");
		list.ascAlphArray();
		System.out.println(list.displayList());
		
		System.out.println("Testing name sort Descending");
		list.desAlphArray();
		System.out.println(list.displayList());
		
		System.out.println("Testing date sort Ascending");
		list.ascDateArray();
		System.out.println(list.displayList());
		
		System.out.println("Testing date sort Descending");
		list.desDateArray();
		System.out.println(list.displayList());
		
		
		
		System.out.println(list.remove("bob")); // Works
		
		System.out.println(list.displayList());
		
	}
	
}
