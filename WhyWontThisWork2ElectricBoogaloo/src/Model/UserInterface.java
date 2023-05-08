package Model;

import java.time.LocalDate;

public interface UserInterface{

    public void editProfile (String Username,String firstName, String lastName,LocalDate dob);
    public void createRecord (String Weight, String Temp, String highBP, String lowBP, String Note);
    public void editRecord (HealthRecord updatedRecord,String Weight, String Temp, String highBP, String lowBP, String Note );
    public void deleteRecord (HealthRecord recordForDeletion);
    public void exportRecords ();
}