package Model;

import java.time.LocalDate;

public interface UserInterface{

    public void editProfile (String Username,String firstName, String lastName,LocalDate dob);
    public void createRecord (String recordID, String Weight, String Temp, String HighBP, String LowBP, String Note);
    public void editRecord (HealthRecord updatedRecord,String Weight, String Temp, String highBP, String lowBP, String Note );
    public void deleteRecord (HealthRecord recordForDeletion);
    public void exportRecords ();
	public void editProfile(String username, String firstName, String lastName, LocalDate dob, byte[] bs);
}