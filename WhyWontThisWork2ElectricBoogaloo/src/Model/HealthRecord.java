package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HealthRecord{
	private String RecordID;
	private String Weight;
	private String Temp;
	private String HighBP;
	private String LowBP;
	private String Note;
	private String date;
	private String time;
	
	public HealthRecord( String recordID, String weight, String temp, String highBP, String lowBP, String note) {
		setRecordID(recordID);
		setWeight(weight);
		setTemp(temp);
		setHighBP(highBP);
		setLowBP(lowBP);
		setNote(note);
		setDate();
		setTime();
	}
	public HealthRecord(String recordID, String weight, String temp, String highBP, String lowBP, String note,
			String date, String time) {
		setRecordID(recordID);
		setWeight(weight);
		setTemp(temp);
		setHighBP(highBP);
		setLowBP(lowBP);
		setNote(note);
		setDate(date);
		setTime(time);
	}
	private void setDate(String date) {
		this.date = date;
		
	}
	private void setRecordID(String recordID) {
		this.RecordID = recordID;
		
	}
	
	public String getID() {
		return this.RecordID;
	}
	
	private void setDate() {
		 	LocalDate cDate = LocalDate.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    this.date = cDate.format(formatter);		
	}
	private void setNote(String note) {
		this.Note = note;
		
	}
	private void setLowBP(String lowBP) {
		this.LowBP = lowBP;
		
	}
	private void setHighBP(String highBP) {
		this.HighBP = highBP;
		
	}
	private void setTemp(String temp) {
		this.Temp = temp;
		
	}
	private void setWeight(String weight) {
		this.Weight = weight;
		
	}
	public String getTime() {
		return this.time;
	}
	public String getDate() {
		return date;
	}
	public String getWeight() {
		return Weight;
	}
	public String getTemp() {
		return Temp;
	}
	public String getHighBP() {
		return HighBP;
	}
	public String getLowBP() {
		return LowBP;
	}
	public String getNote() {
		return Note;
	}
	public void update(String weight, String temp, String highBP, String lowBP, String note) {
		setWeight(weight);
		setTemp(temp);
		setHighBP(highBP);
		setLowBP(lowBP);
		setNote(note);
		System.out.println("update record");
		
	}

	private void setTime(){
		LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeString = time.format(formatter);
	}
	
	private void setTime(String time) {
		this.time = time;
	}

}
