package DiScribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;

public class Event{
	
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
	
	public String getDesc() {
		return desc;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getStart_time() {
		return start_time;
	}
	
	public int getEDT() {
		return EDT;
	}
	
	public String getServer() {
		return server;
	}
	
	public String getChannel() {
		return channel;
	}
	
	public boolean getRecur() {
		return recur;
	}
	
	public String getFrequency() {
		return Frequency;
	}
	
	public int getMax_freq() {
		return max_freq;
	}
	
	public int getNum_ann() {
		return num_ann;
	}
	
	public int[] getAnn_time() {
		return ann_time;
	}
	
	public ArrayList<String> getParty(){
		return party;
	}
	
	
	//Setter
	public void setName(String name) {
		/*if(name.length() > 50) {
			System.out.println("The name of event you set should less than 50 characters.");
			return;
		}*/
		this.name = name;
	}
	
	
	public void setDesc(String desc) {
		/*if(desc.split("\\s+").length > 100) {
			System.out.println("The desc should less than 100 words.");
			return;
		}*/
		this.desc = desc;
	}
	
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	public void setDate(String date) {
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
	
	
	public void setStart_time(String time) {
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
	
	
	public void setServer(String server) {
		this.server = server;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	public void setRecur(Boolean recur) {
		this.recur = recur;
	}
	
	
	public void setFrequency(String Frequency) {
		if(getRecur() == false) {
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
	
	
	public void setMax_freq(int max_freq) {
		if(getRecur() == false) {
			System.out.println("The event will not recur.");
		}
		else {
			if(max_freq <= 0) {
				System.out.println("The times should greater than 0");
			}
			else {
				this.max_freq = max_freq;
				this.setRecur(false);
			}
		}
	}
	
	public void setNum_ann(int num_ann) {
		if(num_ann <= 0) {
			System.out.println("Number of times to announce should greater than 0");
		}
		else {
			this.num_ann = num_ann;
		}
	}
	
	public void setAnn_time(int [] ann_time) {
		if(ann_time.length > getNum_ann()) {
			System.out.println("you only have " + getNum_ann() + " times to announce.");
		}else {
			Arrays.sort(ann_time);
			this.ann_time = ann_time;
		}
	}
	
	public void setParty(ArrayList<String> party) {
		Collections.sort(party);
		this.party = party;
	}
	
	public void addParty(String name, ArrayList<String> party) {
		party.add(name);
		setParty(party);
	}
	
	public void removeParty(String name, ArrayList<String> party) {
		party.remove(name);
		setParty(party);
	}
	
	
	
	@Override 
	public String toString() {
		return String.format("```Name: " + name + "\t\tActive: " + active + "\nDate: " + date /*+" @ " + start_time*/ + "\nDuration: "
					+ EDT + " mins" /*+ %t%tRecurring?: " + recur*/ + "\nDescription: " + desc /*+ "\n--------------------------------\n"
					+ "Number of Announcements: " + num_ann + "%n"
					+ "Announcement #" + "%n" + "--------------------------------%n"
					+ "Participants List: %n" + party */
					+ "```");
	}
	
	// Comparator for name (ascending)
	public static Comparator<Event> nameCompareAsc = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			String eName1 = e1.getName().toUpperCase();
			String eName2 = e2.getName().toUpperCase();
			
			return eName1.compareTo(eName2); 
	}};
	
	// Comparator for name (descending)
	public static Comparator<Event> nameCompareDes = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			String eName1 = e1.getName().toUpperCase();
			String eName2 = e2.getName().toUpperCase();
			
			return eName2.compareTo(eName1); 
	}};
	
	// Comparator for date (Ascending)
	public static Comparator<Event> dateCompareAsc = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			String eDate1 = e1.getDate();
			String eDate2 = e2.getDate();
			
			return eDate1.compareTo(eDate2); 
	}};
	
	// Comparator for date (Descending)
	public static Comparator<Event> dateCompareDes = new Comparator<Event>() {
		public int compare(Event e1, Event e2) {
			String eDate1 = e1.getDate();
			String eDate2 = e2.getDate();
			
			return eDate2.compareTo(eDate1); 
	}};
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Event a = new Event("ddcc");
		int [] ann_time = new int [] {9,10,2,3,5};
		ArrayList<String> party = new ArrayList<String>();
		party.add("Bob");
		party.add("Yingbo");
		party.add("Allen");
		party.add("Penny");
		a.setName("name");
		a.setDesc("Description");
		a.setActive(true);
		a.setDate("2021-12-31");
		a.setStart_time("8:30 pm");
		a.setEDT(33);
		a.setServer("server");
		a.setRecur(true);
		a.setFrequency("daily");
		a.setMax_freq(5);
		a.setNum_ann(5);
		a.setAnn_time(ann_time);
		if(a.getAnn_time()!= null) {
			for(int i = 0; i < ann_time.length; i++) {
				//System.out.print(a.getann_time()[i]);
			}
		}
		a.setParty(party);
		a.addParty("Mary", party);
		a.removeParty("Mary", party);
		System.out.println(a.toString());
	}

	
}
