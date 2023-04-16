import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable, userInterface{

    private String userID;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    ArrayList<Record> records = new ArrayList<>();


    public User(String userID, String username, String password, String firstName, String surname) {
     this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSurname(){
        return surname;
    }

    public void setName(String name){
        this.firstName = name;
    }

    public void setSurname(String name){
        this.surname = name;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public void editProfile(String firstName, String lastName) {
        setName(firstName);
        setSurname(lastName);
    }


    @Override
    public void createRecord(Record newRecord) {
        records.add(newRecord);
    }


    @Override
    public void editRecord(Record updatedRecord) {
        int recordID = updatedRecord.getID();
        records.get(recordID).updateRecord(updatedRecord);
    }


    @Override
    public void deleteRecord(int recordForDeletion) {
        records.remove(recordForDeletion);
    }

    @Override
    public void exportRecords() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportRecords'");
    
    }

    public ArrayList getRecords() {
        return records;
    }

    public Record getRecord(int recordIDTemp) {
        for(Record record : records){
            if(recordIDTemp == record.getID()){
                return record;
            }
            continue;
        }
        return null;
    }

    public String getnextRecordID() {
        return String.valueOf(records.size()+1);
    }
    }