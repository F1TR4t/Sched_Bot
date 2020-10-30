package DiScribe;

import java.util.*;

public class Event {
	
	private String name;
	private String Description;
	private Boolean Active;
	private String Date;
	private String StartTime;
	private String Server;
	private String Channal;
	private Boolean Recur;
	private String Frequency;
	private int MaxFreq;
	private int NumAnnounce;
	private int[] AnnounceTime;
	private ArrayList<String> Participants;
	
	//Constructor
	public Event(String name) {
		this.name = name;
	}
	
	//Getter
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public Boolean getActive() {
		return Active;
	}
	
	public String getDate() {
		return Date;
	}
	
	public String getStartTime() {
		return StartTime;
	}
	
	public String getServer() {
		return Server;
	}
	
	public String getChannal() {
		return Channal;
	}
	
	public Boolean getRecur() {
		return Recur;
	}
	
	public String getFrequency() {
		return Frequency;
	}
	
	public int getMaxFreq() {
		return MaxFreq;
	}
	
	public int getNumAnnounce() {
		return NumAnnounce;
	}
	
	public int[] getAnnounceTime() {
		return AnnounceTime;
	}
	
	public ArrayList<String> getParticipants(){
		return Participants;
	}
	
	
	//Setter
	public void setName(String name) {
		if(name.length() > 50) {
			System.out.println("The name of event you set should less than 50 characters.");
		}
		else {
			this.name = name;
		}
	}
	
	public void setDescription(String Description) {
		if(Description.split("\\s+").length > 10) {
			System.out.println("The Description should less than 100 words.");
		}
		else {
			this.Description = Description;
		}
	}
	
	public void setActive(Boolean Active) {
		this.Active = Active;
	}
	
	@Override public String toString() {
		return name;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Event a = new Event("dd");
		a.setName("sbdfdfgdfgdfgdgdfdgggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
		System.out.println("Name: " + a.name);
		a.setDescription("a a a a a a a a a a a a a");
		System.out.println("Description: " + a.Description);
	}
}
