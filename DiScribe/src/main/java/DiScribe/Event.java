package DiScribe;

import java.util.*;

public class Event implements Comparable {
	
	private String name;
	private String desc;
	private boolean active;
	private String date;
	private int start_time;
	private String server;
	private String channel;
	private boolean recur;
	private String Frequency;
	private int max_freq;
	private int num_ann;
	private int[] ann_time;
	private ArrayList<String> party;
	
	//Constructor
	public Event(String name) {
		this.name = name;
	}
	
	//Getter
	public String getName() {
		return name;
	}
	
	public String getdesc() {
		return desc;
	}
	
	public boolean getactive() {
		return active;
	}
	
	public String getdate() {
		return date;
	}
	
	public int getstart_time() {
		return start_time;
	}
	
	public String getserver() {
		return server;
	}
	
	public String getchannel() {
		return channel;
	}
	
	public boolean getrecur() {
		return recur;
	}
	
	public String getFrequency() {
		return Frequency;
	}
	
	public int getmax_freq() {
		return max_freq;
	}
	
	public int getnum_ann() {
		return num_ann;
	}
	
	public int[] getann_time() {
		return ann_time;
	}
	
	public ArrayList<String> getparty(){
		return party;
	}
	
	
	//Setter
	public void setName(String name) {
		if(name.length() > 50) {
			System.out.println("The name of event you set should less than 50 characters.");
			return;
		}
		this.name = name;
	}
	
	public void setdesc(String desc) {
		if(desc.split("\\s+").length > 10) {
			System.out.println("The desc should less than 100 words.");
		}
		this.desc = desc;
	}
	
	public void setactive(Boolean active) {
		this.active = active;
	}
	
	@Override 
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(Object o) {
		Event e = (Event)o;
		
		if ( this.name.equals(e.name) ) {
			return 0;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Event a = new Event("dd");
		a.setName("sbdfdfgdfgdfgdgdfdgggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
		System.out.println("Name: " + a.name);
		a.setdesc("a a a a a a a a a a a a a");
		System.out.println("desc: " + a.desc);
	}

	
}
