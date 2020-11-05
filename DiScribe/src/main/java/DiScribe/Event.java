package DiScribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event implements Comparable {
	
        private String name;
	private String desc;
	private boolean active;
	private String date;
	private String start_time;
	private int EDT;
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
	
	public String getstart_time() {
		return start_time;
	}
	
	public int getEDT() {
		return EDT;
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
		if(desc.split("\\s+").length > 100) {
			System.out.println("The desc should less than 100 words.");
			return;
		}
		this.desc = desc;
	}
	
	
	public void setactive(Boolean active) {
		this.active = active;
	}
	
	
	public void setdate(String date) {
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(date);
		boolean dateFlag = m.matches();
		
		if(!dateFlag) {
			System.out.println("Date format error, it should be yyyy-mm-dd");
			return;
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		try {
			Date EventDate = formatter.parse(date);   //This is an important step to ensure that the date is correct
			this.date = date;
		}catch(Exception e) {
			System.out.println("Date format error, it should be yyyy-mm-dd");
		}
	}
	
	
	public void setstart_time(String time) {
		this.start_time = time;
	}
	
	
	public void setEDT(int EDT) {
		if(EDT <= 0) {
			System.out.println("The estimate duration time should be greater than 0.");
		}
		else {
			this.EDT = EDT;
		}
	}
	
	
	public void setserver(String server) {
		this.server = server;
	}
	
	public void setchannel(String channel) {
		this.channel = channel;
	}
	
	
	public void setrecur(Boolean recur) {
		this.recur = recur;
	}
	
	
	public void setFrequency(String Frequency) {
		if(getrecur() == false) {
			System.out.println("The event will not recur.");
		}
		else {
			if(Frequency.equals("biweekly")){ this.Frequency = Frequency; }
			else if(Frequency.equals("weekly")) { this.Frequency = Frequency; }
			else if(Frequency.equals("monthly")) {this.Frequency = Frequency; }
			else if(Frequency.equals("daily")) {this.Frequency = Frequency; }
			else {
				System.out.println("You should input one of the {biweekly, weekly, monthly, daily}");
			}
		}
	}
	
	
	public void setmax_freq(int max_freq) {
		if(getrecur() == false) {
			System.out.println("The event will not recur.");
		}
		else {
			if(max_freq <= 0) {
				System.out.println("The times should greater than 0");
			}
			else {
				this.max_freq = max_freq;
				this.setrecur(false);
			}
		}
	}
	
	public void setnum_ann(int num_ann) {
		if(num_ann <= 0) {
			System.out.println("Number of times to announce should greater than 0");
		}
		else {
			this.num_ann = num_ann;
		}
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
