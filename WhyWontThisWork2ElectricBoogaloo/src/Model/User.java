package Model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class User implements UserInterface{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    private LocalDate dobLD;
    private ArrayList<HealthRecord> HealthRecords = new ArrayList<HealthRecord>();
    
    public void user(String userName, String password, String firstName, String lastName, LocalDate formDOB) {
		setUsername(userName);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	
		setDOB(formDOB);
		
	}
    
    public void setDOBLD(LocalDate dob) {
    	this.dobLD = dob;
    }
    public LocalDate getDOBLD() {
    	return this.dobLD;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	private void setDOB(LocalDate dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formDOB = dob.format(formatter);
        setDOBLD(dob);
		this.dob = formDOB;
		
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
		
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
		
	}
	public boolean isValidLogin() {
		return true;
    }
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getlastName() {
		return lastName;
	}
	
	public String getDOB() {
		return dob;
	}
	public LocalDate getDOBDateObject() {

		return dobLD;
	}
	
	public ArrayList<HealthRecord> getRecords() {
		return HealthRecords;
	}

	@Override
	public void editProfile(String username, String firstName, String lastName, LocalDate dob) {
		setUsername(username);
		setFirstName(firstName);
		setLastName(lastName);
		setDOB(dob);	
	}

	@Override
	public void createRecord(String Weight, String Temp, String HighBP, String LowBP, String Note) {
		HealthRecord newRecord = new HealthRecord(Weight,Temp,HighBP,LowBP,Note);
		System.out.println("Record Made");
		HealthRecords.add(newRecord);
		System.out.println("Record added");
		
	}

	@Override
	public void editRecord(HealthRecord selectedRecord, String Weight, String Temp, String HighBP, String LowBP, String Note) {
		System.out.println("user before edit");
		selectedRecord.update(Weight, Temp, HighBP, LowBP, Note);
		
	}

	@Override
	public void deleteRecord(HealthRecord recordForDeletion) {
		HealthRecords.remove(recordForDeletion);
		
	}

	@Override
	public void exportRecords() {
		// TODO Auto-generated method stub
		
	}
}

