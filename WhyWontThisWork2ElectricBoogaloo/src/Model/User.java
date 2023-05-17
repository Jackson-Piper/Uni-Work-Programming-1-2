package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class User implements UserInterface{

	private String userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    private LocalDate dobLD;
    private ArrayList<HealthRecord> HealthRecords = new ArrayList<HealthRecord>();

	public  User(String userID,String userName, String password, String firstName, String lastName, LocalDate formDOB) {
		setuserID(userID);
		setUsername(userName);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	
		setDOB(formDOB);
		
	}
    
    private void setuserID(String userID) {
    	this.userID = userID;		
	}
    
    public String getUserID() {
    	return this.userID;
    }

	public User(String[] user) {
    	            setUsername(user[0]);
    	            setFirstName(user[1]);
    	            setLastName(user[2]);
    	            setDOB(user[3]);
    	            setDOBLD(user[3]);
    	        }

	private void setDOB(String dob) {
		this.dob = dob;
		
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
	
	private void setDOBLD(String dob) {
		String dateString = dob; // example date string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dobLD = LocalDate.parse(dateString, formatter);
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
	public void createRecord(String recordID, String Weight, String Temp, String HighBP, String LowBP, String Note) {
		HealthRecord newRecord = new HealthRecord(recordID,Weight,Temp,HighBP,LowBP,Note);
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
	}

	public String getPassword() {
		return password;
	}
}

