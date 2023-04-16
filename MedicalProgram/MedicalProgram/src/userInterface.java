public interface userInterface{

    public void editProfile (String firstName, String lastName);
    public void createRecord (Record newrecord);
    public void editRecord (Record updatedRecord);
    public void deleteRecord (int recordForDeletion);
    public void exportRecords ();
}