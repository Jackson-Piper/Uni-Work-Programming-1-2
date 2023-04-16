public class User implements userInterface{

    private string userID;
    private string username;
    private string password;
    private string firstName;
    private string Surname;
    ArrayList<record> records = new ArrayList<>();


    public class user(String userID, String username, String password, String firstName, String Surname){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.Surname = Surname;
    }

    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName
    }

    public String getSurname(){
        return Surname;
    }

    public void setName(String name){
        this.firstName = name;
    }

    public void setSurname(String name){
        this.Surname = name;
    }

    public String getPassword(){
        return password;
    }

    //interface methods
    public void editProfile (firstName, lastName){
        setName(firstName);
        setSurname(lastName);
    }

    public void createRecord (newrecord){
        records.add(newRecord);
    }

    public void editRecord (updatedRecord){
        String recordID = updatedRecord.getID();
        records.getRecord(recordID).updateRecord(updatedRecord);
    }

    public void deleteRecord (recordForDeletion){
        records.remove(getIndexOf(recordForDeletion));
    }


    //needs to be completed
    public void exportRecords(){
        
    }




}
